package com.example.softcafeengineer.view.Manager.EditCategories;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuActivity;

import java.util.List;

public class EditCategoriesActivity extends AppCompatActivity implements EditCategoriesView, MenuProductRecyclerViewAdapter.ItemSelectionListener {
    private EditCategoriesViewModel viewModel;
    private String brand, category;
    private RelativeLayout relativeLayout;
    private String prev_category_name, prev_category_desc;

    // Edit category pop up
    private PopupWindow edit_category_popup;
    private EditText editCategoryNameField, editCategoryDescField;
    private Button confirmEditButton;
    private boolean confirm_edit_enabled, text_changed;
    private String newCategoryName, newCategoryDesc;

    // Delete category pop up
    private PopupWindow delete_category_popup;

    // Add new product pop up
    private PopupWindow add_product_popup;
    private EditText addProductNameField, addProductPriceField;
    private Switch setProductAvailabilitySwitch;
    private boolean add_product_switch_status;
    private Button addProductButton;
    private boolean add_product_enabled;
    private String newProductName, newProductPrice;

    private Product selected_product;
    // Edit product pop up
    private PopupWindow edit_product_popup;
    private String prev_product_name;
    private double prev_product_price;
    private EditText editProductNameField, editProductPriceField;

    // Delete product pop up
    private PopupWindow delete_product_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_categories);
        relativeLayout = findViewById(R.id.relative_edit_category); // activity_edit_categories.xml

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
            category = intent.getStringExtra("category_name");
        }

        viewModel = new ViewModelProvider(this).get(EditCategoriesViewModel.class);
        viewModel.getPresenter().setView(this, category, brand); // updates results

        // Fill info shown
        TextView categoryName = findViewById(R.id.txt_category_name);
        prev_category_name = viewModel.getPresenter().getCategoryName();
        categoryName.setText(prev_category_name);
        TextView categoryDesc = findViewById(R.id.txt_category_desc);
        prev_category_desc = viewModel.getPresenter().getCategoryDesc();
        categoryDesc.setText(prev_category_desc);

        List<Product> productList = viewModel.getPresenter().getProductResults();
        // Recycler view
        RecyclerView recyclerView_products = findViewById(R.id.recycler_view_products);
        recyclerView_products.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_products.setAdapter(new MenuProductRecyclerViewAdapter(productList, this));

        Button editCategory = findViewById(R.id.btn_edit_category); // "Edit category" button
        editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_edit_category, null);

                // Create and show edit category popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                edit_category_popup = new PopupWindow(pop_up, width, height, true);
                edit_category_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                editCategoryNameField = pop_up.findViewById(R.id.edit_text_edit_category_name);
                editCategoryNameField.setText(prev_category_name);
                newCategoryName = prev_category_name;
                editCategoryDescField = pop_up.findViewById(R.id.edit_text_edit_category_desc);
                editCategoryDescField.setText(prev_category_desc);
                newCategoryDesc = prev_category_desc;
                editCategoryNameField.addTextChangedListener(editCategoryWatcher);
                editCategoryDescField.addTextChangedListener(editCategoryWatcher);

                confirmEditButton = pop_up.findViewById(R.id.btn_final_edit_category);
                // Confirm button is enabled
                confirm_edit_enabled = true;
                confirmEditButton.setAlpha(1.0f);
                text_changed = false;
                confirmEditButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the confirm button
                    // inside the edit category pop up
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onEditCategory(confirm_edit_enabled, text_changed, prev_category_name, prev_category_desc, newCategoryName, newCategoryDesc);
                    }
                });

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_edit_category);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the edit category pop up
                    @Override
                    public void onClick(View v) {
                        edit_category_popup.dismiss();
                    }
                });
            }
        });

        Button deleteCategory = findViewById(R.id.btn_delete_category); // "Delete category" button
        deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_delete_category, null);

                // Create and show delete category popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                delete_category_popup = new PopupWindow(pop_up, width, height, true);
                delete_category_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                Button confirmDeleteButton = pop_up.findViewById(R.id.btn_final_delete_category);
                confirmDeleteButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the confirm button
                    // inside the delete category pop up
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onDeleteCategory();
                    }
                });

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_delete_category);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the delete category pop up
                    @Override
                    public void onClick(View v) {
                        delete_category_popup.dismiss();
                    }
                });
            }
        });

        Button addNewProduct = findViewById(R.id.btn_add_product); // "Add product" button
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_product, null);

                // Create and show add product popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_product_popup = new PopupWindow(pop_up, width, height, true);
                add_product_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                addProductNameField = pop_up.findViewById(R.id.edit_text_add_product_name);
                addProductPriceField = pop_up.findViewById(R.id.edit_text_add_product_price);
                addProductNameField.addTextChangedListener(newProductWatcher);
                addProductPriceField.addTextChangedListener(newProductWatcher);
                setProductAvailabilitySwitch = pop_up.findViewById(R.id.add_product_availability);
                add_product_switch_status = false;
                setProductAvailabilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        add_product_switch_status = isChecked;
                    }
                });

                addProductButton = pop_up.findViewById(R.id.btn_final_add_product);
                // Add button is disabled
                add_product_enabled = false;
                addProductButton.setAlpha(.5f);
                addProductButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the add button
                    // inside the add new product pop up
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onAddProduct(add_product_enabled, newProductName, newProductPrice, add_product_switch_status);
                    }
                });

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_product);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the add new product pop up
                    @Override
                    public void onClick(View v) {
                        add_product_popup.dismiss();
                    }
                });
            }
        });
    }

    // Edit Category
    // -------------
    TextWatcher editCategoryWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in edit category popup
            text_changed = true;
            newCategoryName = editCategoryNameField.getText().toString().trim();
            newCategoryDesc = editCategoryDescField.getText().toString().trim();
            if (!newCategoryName.isEmpty() && !newCategoryDesc.isEmpty()) {
                confirmEditButton.setAlpha(1.0f);
                confirm_edit_enabled = true;
            } else {
                confirmEditButton.setAlpha(.5f);
                confirm_edit_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // Add new product
    // ---------------
    TextWatcher newProductWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new product popup
            newProductName = addProductNameField.getText().toString().trim();
            newProductPrice = addProductPriceField.getText().toString();
            if (!newProductName.isEmpty() && !newProductPrice.isEmpty()) {
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

    // -------
    // EditMenuView implementations
    // -------
    @Override
    public void successfulEditCategory() {
        // User successfully edited a category
        // Restart activity with an updated category
        edit_category_popup.dismiss();
        Intent intent = new Intent(EditCategoriesActivity.this, EditCategoriesActivity.class);
        intent.putExtra("cafe_brand", brand);
        intent.putExtra("category_name", newCategoryName);
        startActivity(intent);
        EditCategoriesActivity.this.finish();
    }

    @Override
    public void successfulDeleteCategory() {
        // User successfully delete
        // current category
        delete_category_popup.dismiss();
        Intent intent = new Intent(EditCategoriesActivity.this, EditMenuActivity.class);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void successfulNewProduct() {
        // User successfully added a new product
        // Restart activity with new products list
        add_product_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulEditProduct() {
        // User successfully edited a product
        // Restart activity with an updated products list
        edit_product_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulDeleteProduct() {
        // User successfully deleted a product
        // Restart activity with an updated products list
        delete_product_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditCategoriesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------

    // Edit product
    // ------------
    @Override
    public void editProduct(Product p) {
        selected_product = p;
        prev_product_name = p.getName();
        prev_product_price = p.getPrice();
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_edit_product, null);

        // Create and show edit product popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        edit_product_popup = new PopupWindow(pop_up, width, height, true);
        edit_product_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        editProductNameField = pop_up.findViewById(R.id.edit_text_edit_product_name);
        editProductNameField.setText(prev_product_name);
        editProductPriceField = pop_up.findViewById(R.id.edit_text_edit_product_price);
        editProductPriceField.setText(String.format("%.2f", prev_product_price));
        editProductNameField.addTextChangedListener(editProductWatcher);
        editProductPriceField.addTextChangedListener(editProductWatcher);

        Button changeAvailabilityButton = pop_up.findViewById(R.id.btn_edit_availability);
        changeAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            // User clicked on the change availability
            // button inside the edit product pop up
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onChangeAvailabilityOfProduct(selected_product);
            }
        });

        confirmEditButton = pop_up.findViewById(R.id.btn_final_edit_product);
        // Confirm button is enabled
        confirm_edit_enabled = true;
        confirmEditButton.setAlpha(1.0f);
        text_changed = false;
        confirmEditButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the confirm button
            // inside the edit product pop up
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onEditProduct(selected_product, confirm_edit_enabled, text_changed, prev_product_name, prev_product_price, newProductName, newProductPrice);
            }
        });

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_edit_product);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the edit product pop up
            @Override
            public void onClick(View v) {
                edit_product_popup.dismiss();
            }
        });
    }

    TextWatcher editProductWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in edit product popup
            text_changed = true;
            newProductName = editProductNameField.getText().toString().trim();
            newProductPrice = editProductPriceField.getText().toString();
            if (!newProductName.isEmpty() && !newProductPrice.isEmpty()) {
                confirmEditButton.setAlpha(1.0f);
                confirm_edit_enabled = true;
            } else {
                confirmEditButton.setAlpha(.5f);
                confirm_edit_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // Delete product
    // --------------
    @Override
    public void deleteProduct(Product p) {
        selected_product = p;
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_delete_product, null);

        // Create and show delete product popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        delete_product_popup = new PopupWindow(pop_up, width, height, true);
        delete_product_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        Button confirmDeleteButton = pop_up.findViewById(R.id.btn_final_delete_product);
        confirmDeleteButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the confirm button
            // inside the delete product pop up
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onDeleteProduct(selected_product);
            }
        });

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_delete_product);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the delete product pop up
            @Override
            public void onClick(View v) {
                delete_product_popup.dismiss();
            }
        });
    }
}
