package com.example.softcafeengineer.view.Barista.ManageOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<OrderListRecyclerViewAdapter.ViewHolder> {
    private final List<OrderInfo> mValues;
    private final OrderListRecyclerViewAdapter.ItemSelectionListener listener;

    public OrderListRecyclerViewAdapter(List<OrderInfo> items, OrderListRecyclerViewAdapter.ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderListRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListRecyclerViewAdapter.ViewHolder holder, int position) {
        final OrderInfo currentOrderInfo = mValues.get(position);
        String product_name = currentOrderInfo.getProduct().getName();
        holder.productName.setText(product_name);
        int quantity = currentOrderInfo.getQuantity();
        holder.quantity.setText(String.valueOf(quantity));
        String desc = currentOrderInfo.getDescription();
        holder.description.setText(desc);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editOrderInfo(currentOrderInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView productName, quantity, description;
        public final Button editButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            productName = view.findViewById(R.id.order_list_layout_product_name);
            quantity = view.findViewById(R.id.order_list_layout_quantity);
            description = view.findViewById(R.id.order_list_layout_desc);
            editButton = view.findViewById(R.id.order_list_layout_btn_edit);
        }
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "Edit"
         * button for a specific order info
         */
        void editOrderInfo(OrderInfo o);
    }
}
