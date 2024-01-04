package com.example.softcafeengineer.view.Barista.Actions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Order;

import java.util.List;

public class ActiveOrdersRecyclerViewAdapter extends RecyclerView.Adapter<ActiveOrdersRecyclerViewAdapter.ViewHolder>
{
    private final List<Order> mValues;
    private final ItemSelectionListener listener;

    public ActiveOrdersRecyclerViewAdapter(List<Order> items, ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActiveOrdersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.active_order_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveOrdersRecyclerViewAdapter.ViewHolder holder, int position) {
        final Order currentOrder = mValues.get(position);
        holder.tableNumber.setText(String.valueOf(currentOrder.getTable().getId()));
        holder.totalCost.setText(String.format("%.2f 💶", currentOrder.getTotalCost()));

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.viewOrder(currentOrder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tableNumber, totalCost;
        public final Button viewButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            tableNumber = view.findViewById(R.id.active_order_layout_table_number);
            totalCost = view.findViewById(R.id.active_order_layout_total_cost);
            viewButton = view.findViewById(R.id.active_order_layout_btn_view);
        }
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "View"
         * button for a specific order
         */
        void viewOrder(Order o);
    }
}
