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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.active_orders_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveOrdersRecyclerViewAdapter.ViewHolder holder, int position) {
        final Order currentOrder = mValues.get(position);
        // holder.tableNumber.setText(String.valueOf(currentOrder.getId()));
        // holder.tableUniqueId.setText(String.valueOf(currentOrder.getQRCode()));

//        holder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.editTable(currentOrder);
//            }
//        });
//
//        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { listener.deleteTable(currentOrder); }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final TextView tableNumber, tableUniqueId;
//        public final Button editButton, deleteButton;

        public ViewHolder(@NonNull View view) {
            super(view);
//            tableNumber = view.findViewById(R.id.table_layout_table_number);
//            tableUniqueId = view.findViewById(R.id.table_layout_table_id);
//            editButton = view.findViewById(R.id.table_layout_btn_edit);
//            deleteButton = view.findViewById(R.id.table_layout_btn_delete);
        }
    }

    public interface ItemSelectionListener {

    }
}
