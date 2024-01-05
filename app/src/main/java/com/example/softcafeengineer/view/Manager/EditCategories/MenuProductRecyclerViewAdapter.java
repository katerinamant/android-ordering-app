package com.example.softcafeengineer.view.Manager.EditCategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Product;

import java.util.List;

public class MenuProductRecyclerViewAdapter extends RecyclerView.Adapter<MenuProductRecyclerViewAdapter.ViewHolder>
{
    private final List<Product> mValues;
    private final MenuProductRecyclerViewAdapter.ItemSelectionListener listener;

    public MenuProductRecyclerViewAdapter(List<Product> items, MenuProductRecyclerViewAdapter.ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.product_menu_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuProductRecyclerViewAdapter.ViewHolder holder, int position) {
        final Product currentProduct = mValues.get(position);
        holder.name.setText(String.valueOf(currentProduct.getName()));
        holder.price.setText(String.format("%s 💶",String.valueOf(currentProduct.getPrice())));
        if(currentProduct.getAvailability()) {
            holder.availability.setText("🟩");
        } else{
            holder.availability.setText("🟥");
        }

        holder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { listener.editProduct(currentProduct); }
        });

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { listener.deleteProduct(currentProduct); }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, price, availability;

        public final Button edit_button, delete_button;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.product_layout_name);
            price = view.findViewById(R.id.product_layout_price);
            availability = view.findViewById(R.id.product_layout_availability);
            edit_button = view.findViewById(R.id.product_layout_btn_edit);
            delete_button = view.findViewById(R.id.product_layout_btn_delete);
        }
    }

    public interface  ItemSelectionListener {
        /**
         * User clicked the "Edit"
         * button for a specific product
         */
        void editProduct(Product p);

        /**
         * User clicked the "Delete"
         * button for a specific product
         */
        void deleteProduct(Product p);
    }
}
