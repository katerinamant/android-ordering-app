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

public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<OrderListRecyclerViewAdapter.ViewHolder>
{
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
        holder.productName.setText(String.valueOf(currentOrderInfo.getProduct().getName()));
        holder.quantity.setText(String.valueOf(currentOrderInfo.getQuantity()));
        holder.description.setText(String.valueOf(currentOrderInfo.getDescription()));
        holder.manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.manageOrderInfo(currentOrderInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView productName, quantity, description;
        public final Button manageButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            productName = view.findViewById(R.id.order_list_layout_product_name);
            quantity = view.findViewById(R.id.order_list_layout_quantity);
            description = view.findViewById(R.id.order_list_layout_desc);
            manageButton = view.findViewById(R.id.order_list_layout_btn_manage);
        }
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "Manage"
         * button for a specific order info
         */
        void manageOrderInfo(OrderInfo o);
    }
}
