package com.example.softcafeengineer.view.Manager.EditMenu;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>
{
    private final List<ProductCategory> mValues;
    private final ItemSelectionListener listener;

    public CategoryRecyclerViewAdapter(List<ProductCategory> items, ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, int position) {
        final ProductCategory currentCategory = mValues.get(position);
        holder.name.setText(String.valueOf(currentCategory.getName()));
        holder.description.setText(String.valueOf(currentCategory.getDescription()));

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { listener.viewCategory(currentCategory);}
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, description;
        public final Button viewButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.category_layout_name);
            description = view.findViewById(R.id.category_layout_description);
            viewButton = view.findViewById(R.id.category_layout_btn_view);
        }
    }

    public interface  ItemSelectionListener {
        /**
         * User clicked the "View"
         * button for a specific category
         */
        void viewCategory(ProductCategory c);
    }
}
