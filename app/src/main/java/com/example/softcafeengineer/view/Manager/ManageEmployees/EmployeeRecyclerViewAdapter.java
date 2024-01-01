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
    public EmployeeRecyclerViewAdapter(List<Barista> b, ItemSelectionListener listener){
        this.mValues = b;
        this.listener = listener;
    }
    @NonNull
    @Override
    public EmployeeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_iem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeRecyclerViewAdapter.ViewHolder holder, int position) {
        final Barista currentEmployee = mValues.get(position);
        holder.employee_password.setText(String.valueOf(currentEmployee.getPassword()));
        holder.employee_username.setText(String.valueOf(currentEmployee.getUsername()));

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteEmployee(currentEmployee);
            }
        });
        holder.edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.editEmployee(currentEmployee);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView employee_password, employee_username;
        public final Button delete_button;
        public final Button edit_button;
        public ViewHolder(@NonNull View view) {
            super(view);
            employee_password = view.findViewById(R.id.employee_layout_username);
            employee_username = view.findViewById(R.id.empoyee_layout_password);
            delete_button = view.findViewById(R.id.employee_layout_btn_delete);
            edit_button = view.findViewById(R.id.employee_layout_btn_edit);
        }
    }
    public interface ItemSelectionListener{
        void deleteEmployee(Barista b);

        void editEmployee(Barista b);
    }
}
