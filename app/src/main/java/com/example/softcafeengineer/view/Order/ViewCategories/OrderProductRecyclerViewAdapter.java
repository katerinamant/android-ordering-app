package com.example.softcafeengineer.view.Order.ViewCategories;

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

public class OrderProductRecyclerViewAdapter extends RecyclerView.Adapter<OrderProductRecyclerViewAdapter.ViewHolder> {
    private final List<Product> mValues;
    private final OrderProductRecyclerViewAdapter.ItemSelectionListener listener;

    public OrderProductRecyclerViewAdapter(List<Product> items, OrderProductRecyclerViewAdapter.ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.product_order_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductRecyclerViewAdapter.ViewHolder holder, int position) {
        final Product currentProduct = mValues.get(position);
        String name = currentProduct.getName();
        holder.name.setText(name);
        double price = currentProduct.getPrice();
        holder.price.setText(String.format("%s 💶",price));

        holder.add_to_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addProductToCart(currentProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView name, price;

        public final Button add_to_cart_button;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.product_layout_order_name);
            price = view.findViewById(R.id.product_layout_order_price);
            add_to_cart_button = view.findViewById(R.id.product_layout_order_btn_add_to_cart);
        }
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "Add to cart"
         * button for a specific product
         */
        void addProductToCart(Product p);
    }
}
