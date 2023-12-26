package com.example.softcafeengineer.view.Manager.ManageTables;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Table;

import java.util.List;

public class TableRecyclerViewAdapter extends RecyclerView.Adapter<TableRecyclerViewAdapter.ViewHolder>
{
    private final List<Table> mValues;
    private final ItemSelectionListener listener;

    public TableRecyclerViewAdapter(List<Table> items, ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TableRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TableRecyclerViewAdapter.ViewHolder holder, int position) {
        final Table currentTable = mValues.get(position);
        holder.tableNumber.setText(String.valueOf(currentTable.getId()));
        holder.tableUniqueId.setText(String.valueOf(currentTable.getQRCode()));

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteTable(currentTable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tableNumber, tableUniqueId;
        public final Button delete_button;

        public ViewHolder(@NonNull View view) {
            super(view);
            tableNumber = view.findViewById(R.id.table_layout_table_number);
            tableUniqueId = view.findViewById(R.id.table_layout_table_id);
            delete_button = view.findViewById(R.id.table_layout_btn_delete);
        }
    }

    public interface ItemSelectionListener {
        void deleteTable(Table t);
    }
}
