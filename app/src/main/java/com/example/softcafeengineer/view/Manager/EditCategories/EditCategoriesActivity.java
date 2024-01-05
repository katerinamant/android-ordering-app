package com.example.softcafeengineer.view.Manager.EditCategories;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.example.softcafeengineer.view.Manager.Actions.ManagerActionsActivity;
import com.example.softcafeengineer.view.Manager.EditMenu.CategoryRecyclerViewAdapter;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuActivity;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuViewModel;

import java.util.List;

public class EditCategoriesActivity extends AppCompatActivity implements EditCategoriesView, ProductRecyclerViewAdapter.ItemSelectionListener{
    private EditCategoriesViewModel viewModel;
    private String brand;
    private RelativeLayout relativeLayout;

    // Add new category pop up
    private PopupWindow add_product_popup;
    private EditText addProductNameField, addProductPriceField;
    private Switch setProductAvailabilitySwitch;
    private Button addProductButton;
    private boolean add_product_enabled;
    private String newProductName;
    private double newProductPrice;
    private boolean newProductAvailability;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_of_cat_list_item);

        if(savedInstanceState == null){
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(EditCategoriesViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrand(brand); // updates results
        List<Product> productList = viewModel.getPresenter().getProductResults();
        // Recycler view
        RecyclerView recyclerView_products = findViewById(R.id.recycler_view_products);
        recyclerView_products.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_products.setAdapter(new ProductRecyclerViewAdapter(productList, this));

        Button addNewProduct = findViewById(R.id.btn_add_product); // "Add category" button
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_edit_category); // activity_edit_menu.xml
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_product, null);

                // Create and show add category popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_product_popup = new PopupWindow(pop_up, width,height, true);
                add_product_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                addProductNameField = pop_up.findViewById(R.id.prod_layout_name);
                addProductPriceField = pop_up.findViewById(R.id.prod_layout_price);
                addProductNameField.addTextChangedListener(newProductWatcher);
                addProductPriceField.addTextChangedListener(newProductWatcher);
                setProductAvailabilitySwitch = pop_up.findViewById(R.id.prod_layout_availability);
                setProductAvailabilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.v("Switch State=", ""+isChecked);
                    }
                });

                addProductButton = pop_up.findViewById(R.id.btn_add_new_product);
                addProductButton.setOnClickListener(onAddProductButton);
                // Add button is disabled
                add_product_enabled = false;
                addProductButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_product);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the add new category pop up
                    @Override
                    public void onClick(View v) {
                        add_product_popup.dismiss();
                    }
                });
            }
        });
    }

    TextWatcher newProductWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new category popup
            newProductName = addProductNameField.getText().toString().trim();
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

    View.OnClickListener onAddProductButton = new View.OnClickListener() {
        // User clicked the add button
        // inside the add new category pop up
        @Override
        public void onClick(View v){
            viewModel.getPresenter().onAddProduct(add_product_enabled, newProductName, newProductPrice, newProductAvailability, getIntent().getStringExtra("category_name"));
        }
    };

    // -------
    // EditMenuView implementations
    // -------
    @Override
    public void successfulNewProduct() {
        // User successfully added a new product category
        // Restart activity with new product categories list
        add_product_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditCategoriesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void viewProduct(Product p) {
        Intent intent = new Intent(EditCategoriesActivity.this, ManagerActionsActivity.class); // placeholder for ViewCategoryActivity
        intent.putExtra("cafe_brand", brand);
        intent.putExtra("product_name", p.getName());
        startActivity(intent);
    }

}
