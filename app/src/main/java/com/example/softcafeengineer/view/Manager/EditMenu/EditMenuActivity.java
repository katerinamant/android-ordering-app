package com.example.softcafeengineer.view.Manager.EditMenu;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.view.Manager.EditCategories.EditCategoriesActivity;

import java.util.List;

public class EditMenuActivity extends AppCompatActivity implements EditMenuView, CategoryRecyclerViewAdapter.ItemSelectionListener {
    private EditMenuViewModel viewModel;
    private String brand;
    private RelativeLayout relativeLayout;

    // Add new category pop up
    private PopupWindow add_category_popup;
    private EditText addCategoryNameField, addCategoryDescriptionField;
    private Button addCategoryButton;
    private boolean add_category_enabled;
    private String newCategoryName, newCategoryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_edit_menu); // activity_edit_menu.xml

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(EditMenuViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrand(brand); // updates results
        List<ProductCategory> categoryList = viewModel.getPresenter().getCategoryResults();
        // Recycler view
        RecyclerView recyclerView_categories = findViewById(R.id.recycler_view_categories_menu);
        recyclerView_categories.setLayoutManager(new LinearLayoutManager(this));
        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(categoryList, this);
        recyclerView_categories.setAdapter(categoryRecyclerViewAdapter);

        Button addNewCategory = findViewById(R.id.btn_add_category); // "Add category" button
        addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_category, null);

                // Create and show add category popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_category_popup = new PopupWindow(pop_up, width, height, true);
                add_category_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                addCategoryNameField = pop_up.findViewById(R.id.edit_text_add_category_name);
                addCategoryDescriptionField = pop_up.findViewById(R.id.edit_text_add_category_description);
                addCategoryNameField.addTextChangedListener(newCategoryWatcher);
                addCategoryDescriptionField.addTextChangedListener(newCategoryWatcher);

                addCategoryButton = pop_up.findViewById(R.id.btn_final_add_category);
                addCategoryButton.setOnClickListener(onAddCategoryButton);
                // Add button is disabled
                add_category_enabled = false;
                addCategoryButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_category);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the add new category pop up
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in add new category popup
            newCategoryName = addCategoryNameField.getText().toString().trim();
            newCategoryDescription = addCategoryDescriptionField.getText().toString().trim();
            if (!newCategoryName.isEmpty() && !newCategoryDescription.isEmpty()) {
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

    View.OnClickListener onAddCategoryButton = new View.OnClickListener() {
        // User clicked the add button
        // inside the add new category pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onAddCategory(add_category_enabled, newCategoryName, newCategoryDescription);
        }
    };

    @Override
    protected void onRestart() {
        // If a category was modified
        // the activity must be restarted
        // to show the new name
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    // -------
    // EditMenuView implementations
    // -------
    @Override
    public void successfulNewCategory() {
        // User successfully added a new product category
        // Restart activity with new product categories list
        add_category_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditMenuActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void viewCategory(ProductCategory c) {
        Intent intent = new Intent(EditMenuActivity.this, EditCategoriesActivity.class);
        intent.putExtra("cafe_brand", brand);
        intent.putExtra("category_name", c.getName());
        startActivity(intent);
    }
}
