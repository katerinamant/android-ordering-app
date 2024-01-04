package com.example.softcafeengineer.view.Manager.EditMenu;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.view.Manager.ManageTables.TableRecyclerViewAdapter;

import java.util.List;

public class EditMenuViewAdapter extends RecyclerView.Adapter<EditMenuViewAdapter.ViewHolder> {
    private final List<ProductCategory> CatValues;
   private final ItemSelectionListener listener;
   public EditMenuViewAdapter(List<ProductCategory> items, ItemSelectionListener listener){
       this.CatValues = items;
       this.listener = listener;
   }
    @NonNull
    @Override
    public EditMenuViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull EditMenuViewAdapter.ViewHolder holder, int position) {
        final ProductCategory currentCategory = CatValues.get(position);
        holder.name.setText(String.valueOf(currentCategory.getName()));
        holder.description.setText(String.valueOf(currentCategory.getDescription()));

    }

    @Override
    public int getItemCount() {
        return CatValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, description;
        public boolean available;
        public double price;
        //public final Button deleteButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.category_layout_name);
            description = view.findViewById(R.id.category_layout_description);
            //deleteButton = view.findViewById(R.id.btn_delete_product);
            //available = view.findViewById(R.id.prod_layout_availability);
        }
    }
    public interface  ItemSelectionListener{
        void editProduct(Product p);
        void deleteProduct(Product p);
        void editCategory(ProductCategory category);
        void deleteCategory(ProductCategory category);
    }
}
