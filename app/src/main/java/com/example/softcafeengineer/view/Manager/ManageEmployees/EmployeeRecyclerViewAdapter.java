package com.example.softcafeengineer.view.Manager.ManageEmployees;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Barista;

import java.util.List;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder> {
    private final List<Barista> mValues;
    private final ItemSelectionListener listener;

    public EmployeeRecyclerViewAdapter(List<Barista> items, ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeRecyclerViewAdapter.ViewHolder holder, int position) {
        final Barista currentEmployee = mValues.get(position);
        String username = currentEmployee.getUsername();
        holder.employee_username.setText(username);
        String password = currentEmployee.getPassword();
        holder.employee_password.setText(password);

        holder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editEmployee(currentEmployee);
            }
        });
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteEmployee(currentEmployee);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView employee_password, employee_username;
        public final Button edit_button, delete_button;

        public ViewHolder(@NonNull View view) {
            super(view);
            employee_username = view.findViewById(R.id.employee_layout_username);
            employee_password = view.findViewById(R.id.employee_layout_password);
            edit_button = view.findViewById(R.id.employee_layout_btn_edit);
            delete_button = view.findViewById(R.id.employee_layout_btn_delete);
        }
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "Edit"
         * button for a specific employee
         */
        void editEmployee(Barista b);

        /**
         * User clicked the "Delete"
         * button for a specific employee
         */
        void deleteEmployee(Barista b);
    }
}
