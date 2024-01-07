package com.example.softcafeengineer.view.Order.ViewMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.view.Manager.EditMenu.CategoryRecyclerViewAdapter;
import com.example.softcafeengineer.view.Order.ViewCart.ViewCartActivity;
import com.example.softcafeengineer.view.Order.ViewCategories.ViewCategoriesActivity;

import java.util.List;

public class ViewMenuActivity extends AppCompatActivity implements ViewMenuView, CategoryRecyclerViewAdapter.ItemSelectionListener
{
    private ViewMenuViewModel viewModel;
    private String unique_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        if(savedInstanceState == null){
            Intent intent = getIntent();
            unique_id = intent.getStringExtra("unique_id");
        }

        viewModel = new ViewModelProvider(this).get(ViewMenuViewModel.class);
        viewModel.getPresenter().setView(this, unique_id); // updates results

        List<ProductCategory> categoryList = viewModel.getPresenter().getCategoryResults();
        // Recycler view
        RecyclerView recyclerView_categories = findViewById(R.id.recycler_view_categories_order);
        recyclerView_categories.setLayoutManager(new LinearLayoutManager(this));
        // We are using the same
        // recycler view adapter
        // with the EditMenuActivity
        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(categoryList, this);
        recyclerView_categories.setAdapter(categoryRecyclerViewAdapter);

        Button viewCartButton = findViewById(R.id.btn_view_cart);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onViewCart();
            }
        });
    }

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
    // ViewMenuView implementations
    // -------
    @Override
    public void onViewingCart() {
        Intent intent = new Intent(ViewMenuActivity.this, ViewCartActivity.class);
        intent.putExtra("unique_id", unique_id);
        startActivity(intent);
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void viewCategory(ProductCategory c) {
        Intent intent = new Intent(ViewMenuActivity.this, ViewCategoriesActivity.class);
        intent.putExtra("cafe_brand", c.getCafe().getBrand());
        intent.putExtra("category_name", c.getName());
        intent.putExtra("unique_id", unique_id);
        startActivity(intent);
    }
}
