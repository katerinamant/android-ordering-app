package com.example.softcafeengineer.view.Manager.EditMenu;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.List;

public class EditMenuActivity extends AppCompatActivity implements EditMenuView, EditMenuViewAdapter.ItemSelectionListener {
    private EditMenuViewModel viewModel;
    private String brand;
    private RelativeLayout relativeLayout;
    private PopupWindow add_product_popup;
    private PopupWindow add_category_popup;
    private EditText addProductNameField;
    private EditText addProductPriceField;
    private Switch setProductAvailabilitySwitch;
    private EditText addCategoryNameField;
    private EditText addCategoryDescriptionField;
    private Button addProductButton;
    private Button addCategoryButton;
    private boolean add_product_enabled;
    private boolean add_category_enabled;
    private Product selected_product;
    private ProductCategory selected_category;
    private String prev_cat_name;
    private String prev_cat_descr;
    private String newProductName;
    private double newProductPrice;
    private boolean newProductAvailability;
    private ProductCategory newProductCategory;
    private String newCategoryName;
    private String newCategoryDescription;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        if(savedInstanceState == null){
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(EditMenuViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrandP(brand);
        List<ProductCategory> categoryList = viewModel.getPresenter().getCategoryResults();
        RecyclerView recyclerViewCat = findViewById(R.id.recycler_view_categories);
        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCat.setAdapter(new EditMenuViewAdapter(categoryList, this));

        Button addNewProduct = findViewById(R.id.btn_add_product);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_edit_menu);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_product, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_product_popup = new PopupWindow(pop_up, width, height, true);
                add_product_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                addProductNameField = pop_up.findViewById(R.id.prod_layout_name);
                addProductNameField.addTextChangedListener(newProductWatcher);
                addProductPriceField = pop_up.findViewById(R.id.prod_layout_price);
                addProductPriceField.addTextChangedListener(newProductWatcher);
                setProductAvailabilitySwitch = pop_up.findViewById(R.id.prod_layout_availability);
                setProductAvailabilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.v("Switch State=", ""+isChecked);
                    }
                });
                AutoCompleteTextView categoriesTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_categories);
                ArrayAdapter<ProductCategory> adapter = new ArrayAdapter<ProductCategory>(add_product_popup.getContentView().getContext(), android.R.layout.simple_dropdown_item_1line, viewModel.getPresenter().findAllCategories(brand));
                categoriesTextView.setAdapter(adapter);

                addProductButton = pop_up.findViewById(R.id.btn_add_new_product);
                addProductButton.setOnClickListener(onAddProductButton);
                add_product_enabled = false;
                addProductButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_product);
                cancelButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        add_product_popup.dismiss();
                    }
                });
            }
        });
        Button addNewCategory = findViewById(R.id.btn_add_category);
        addNewCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_category, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_category_popup = new PopupWindow(pop_up, width,height, true);
                add_category_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                addCategoryNameField = pop_up.findViewById(R.id.edit_text_category_name);
                addCategoryNameField.addTextChangedListener(newCategoryWatcher);
                addCategoryDescriptionField = pop_up.findViewById(R.id.edit_text_category_description);
                addCategoryDescriptionField.addTextChangedListener(newCategoryWatcher);

                addCategoryButton = pop_up.findViewById(R.id.btn_add_new_category);
                addCategoryButton.setOnClickListener(onAddCategoryButton);
                add_category_enabled = false;
                addCategoryButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_category);
                cancelButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        add_category_popup.dismiss();
                    }
                });
            }
        });
    }
    TextWatcher newCategoryWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new table popup
            newCategoryName = addCategoryNameField.getText().toString();
            newCategoryDescription = addCategoryDescriptionField.getText().toString();
            if(!newCategoryName.isEmpty() && !newCategoryDescription.isEmpty()) {
                addCategoryButton.setAlpha(1.0f);
                add_category_enabled = true;
            } else {
                addCategoryButton.setAlpha(.5f);
                add_category_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher newProductWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new table popup
            newProductName = addProductNameField.getText().toString();
            newProductPrice = Double.parseDouble(addProductPriceField.getText().toString());
            newProductAvailability = setProductAvailabilitySwitch.isActivated();

            if(!newProductName.isEmpty() && newProductPrice>0) {
                addProductButton.setAlpha(1.0f);
                add_product_enabled = true;
            } else {
                addProductButton.setAlpha(.5f);
                add_product_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    View.OnClickListener onAddProductButton = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            viewModel.getPresenter().onAddProduct(add_product_enabled, newProductName, newProductCategory, newProductAvailability, newProductPrice);
        }
    };
    View.OnClickListener onAddCategoryButton = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            viewModel.getPresenter().onAddCategory(add_category_enabled, newCategoryName, newCategoryDescription);
        }
    };
    public void showToast(String msg) {
        Toast.makeText(EditMenuActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }
    public void successfulNewProduct(){
        add_product_popup.dismiss();
        finish();
        startActivity(getIntent());
    }
    public void successfulNewCategory(){
        add_category_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void editProduct(Product p) {

    }

    @Override
    public void deleteProduct(Product p) {

    }

    @Override
    public void editCategory(ProductCategory category) {

    }

    @Override
    public void deleteCategory(ProductCategory category) {

    }
}
