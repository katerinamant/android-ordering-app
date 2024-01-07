package com.example.softcafeengineer.view.Order.ViewCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Product;

import java.util.List;

public class ViewCategoriesActivity extends AppCompatActivity implements ViewCategoriesView, OrderProductRecyclerViewAdapter.ItemSelectionListener
{
    private ViewCategoriesViewModel viewModel;
    private String brand, category, unique_id;
    private RelativeLayout relativeLayout;

    // Add to cart pop up
    private PopupWindow add_to_cart_popup;
    private Product selected_product;
    private EditText chooseQuantityField, commentsField;
    private Button confirmAddToCartButton;
    private boolean confirm_enabled;
    private String quantity, comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        if(savedInstanceState == null){
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
            category = intent.getStringExtra("category_name");
            unique_id = intent.getStringExtra("unique_id");
        }

        viewModel = new ViewModelProvider(this).get(ViewCategoriesViewModel.class);
        viewModel.getPresenter().setView(this, category, brand, unique_id); // updates results

        // Fill info shown
        TextView categoryName = findViewById(R.id.txt_order_category_name);
        categoryName.setText(viewModel.getPresenter().getCategoryName());
        TextView categoryDesc = findViewById(R.id.txt_order_category_desc);
        categoryDesc.setText(viewModel.getPresenter().getCategoryDesc());

        List<Product> productList = viewModel.getPresenter().getProductResults();
        // Recycler view
        RecyclerView recyclerView_products = findViewById(R.id.recycler_view_products_order);
        recyclerView_products.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_products.setAdapter(new OrderProductRecyclerViewAdapter(productList, this));

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_view_category); // activity_view_categories.xml
    }

    // -------
    // EditMenuView implementations
    // -------
    @Override
    public void successfulAddToCart() {
        // User successfully added
        // a product to cart
        // Restart activity
        add_to_cart_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ViewCategoriesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void addProductToCart(Product p) {
        selected_product = p;
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_add_to_cart, null);

        // Create and show add to cart popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        add_to_cart_popup = new PopupWindow(pop_up, width, height, true);
        add_to_cart_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        chooseQuantityField = pop_up.findViewById(R.id.edit_text_choose_quantity);
        commentsField = pop_up.findViewById(R.id.edit_text_comments);
        chooseQuantityField.addTextChangedListener(addToCartWatcher);
        commentsField.addTextChangedListener(addToCartWatcher);
        comments = "";

        confirmAddToCartButton = pop_up.findViewById(R.id.btn_final_add_to_cart);
        // Confirm button is disabled
        confirm_enabled = false;
        confirmAddToCartButton.setAlpha(.5f);
        confirmAddToCartButton.setOnClickListener(new View.OnClickListener() {
            // User clicked on the confirm button
            // inside the add to cart pop up
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onConfirmAddToCart(selected_product, confirm_enabled, quantity, comments);
            }
        });

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_to_cart);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the add to cart pop up
            @Override
            public void onClick(View v) {
                add_to_cart_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    TextWatcher addToCartWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in add to cart popup
            quantity = chooseQuantityField.getText().toString();
            comments = commentsField.getText().toString();
            if(!quantity.isEmpty()) {
                // Comments are not required
                confirmAddToCartButton.setAlpha(1.0f);
                confirm_enabled = true;
            } else {
                confirmAddToCartButton.setAlpha(.5f);
                confirm_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
