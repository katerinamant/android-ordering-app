package com.example.softcafeengineer.view.Manager.ManageEmployees;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Barista;

import java.util.List;

public class ManageEmployeesActivity extends AppCompatActivity implements ManageEmployeesView, EmployeeRecyclerViewAdapter.ItemSelectionListener
{
    private ManageEmployeesViewModel viewModel;
    private String brand;
    private RelativeLayout relativeLayout;

    // Add new employee pop up
    private PopupWindow add_employee_popup;
    private EditText addUsernameField, addPasswordField;
    private Button addEmployeeButton;
    private boolean add_employee_enabled;

    private Barista selected_barista;
    // Edit employee pop up
    private PopupWindow edit_employee_popup;
    private String prev_username, prev_password;
    private EditText editUsernameField, editPasswordField;
    private Button confirmEditButton;
    private boolean confirm_edit_enabled, text_changed;
    private String newUsername, newPassword;

    // Delete employee pop up
    private PopupWindow delete_employee_popup;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employees);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(ManageEmployeesViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrand(brand); // updates employee results
        List<Barista> employeesList = viewModel.getPresenter().getEmployeeResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EmployeeRecyclerViewAdapter(employeesList, this));

        Button addNewButton = findViewById(R.id.btn_manage_employees_add); // "Add new" button
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_manage_employees); // activity_manage_employees.xml layout
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_employee, null);

                // Create and show add employee popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_employee_popup = new PopupWindow(pop_up, width, height, true);
                add_employee_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                addUsernameField = pop_up.findViewById(R.id.edit_text_add_employee_username);
                addPasswordField = pop_up.findViewById(R.id.edit_text_add_employee_password);
                addUsernameField.addTextChangedListener(newEmployeeWatcher);
                addPasswordField.addTextChangedListener(newEmployeeWatcher);

                addEmployeeButton = pop_up.findViewById(R.id.btn_final_add_employee);
                addEmployeeButton.setOnClickListener(onAddEmployeeButton);
                // Add button is disabled
                add_employee_enabled = false;
                addEmployeeButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_employee);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the add new employee pop up
                    @Override
                    public void onClick(View v) {
                        add_employee_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
                    }
                });
            }
        });
    }

    TextWatcher newEmployeeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new employee popup
            newUsername = addUsernameField.getText().toString();
            newPassword = addPasswordField.getText().toString();
            if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                addEmployeeButton.setAlpha(1.0f);
                addEmployeeButton.setAlpha(1.0f);
                add_employee_enabled = true;
            } else {
                addEmployeeButton.setAlpha(.5f);
                add_employee_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener onAddEmployeeButton = new View.OnClickListener() {
        // User clicked the add button
        // inside the add new employee pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onAddNewEmployee(add_employee_enabled, newUsername, newPassword);
        }
    };

    // -------
    // ManageEmployeeView implementations
    // -------
    @Override
    public void successfulNewEmployee() {
        // User successfully added a new employee
        // Restart activity with new employees list
        add_employee_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulEdit() {
        // User successfully edited an employee
        // Restart activity with an updated employees list
        edit_employee_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulDelete() {
        // User successfully deleted an employee
        // Restart activity with an updated employees list
        delete_employee_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ManageEmployeesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void editEmployee(Barista b) {
        selected_barista = b;
        prev_username = b.getUsername();
        prev_password = b.getPassword();
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_edit_employee, null);

        // Create and show edit employee popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        edit_employee_popup = new PopupWindow(pop_up, width, height, true);
        edit_employee_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        editUsernameField = pop_up.findViewById(R.id.edit_text_edit_employee_username);
        editUsernameField.setText(prev_username);
        editPasswordField = pop_up.findViewById(R.id.edit_text_edit_employee_password);
        editPasswordField.setText(prev_password);
        editUsernameField.addTextChangedListener(editEmployeeWatcher);
        editPasswordField.addTextChangedListener(editEmployeeWatcher);

        confirmEditButton = pop_up.findViewById(R.id.btn_final_edit_employee);
        confirmEditButton.setOnClickListener(onConfirmEditButton);
        // Confirm button is enabled
        confirm_edit_enabled = true;
        confirmEditButton.setAlpha(1.0f);

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_edit_employee);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the edit employee pop up
            @Override
            public void onClick(View v) {
                edit_employee_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    TextWatcher editEmployeeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in edit employee popup
            text_changed = true;
            newUsername = editUsernameField.getText().toString();
            newPassword = editPasswordField.getText().toString();
            if(!newUsername.isEmpty() && !newPassword.isEmpty()) {
                confirmEditButton.setAlpha(1.0f);
                confirm_edit_enabled = true;
            } else {
                confirmEditButton.setAlpha(.5f);
                confirm_edit_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener onConfirmEditButton = new View.OnClickListener() {
        // User clicked the confirm button
        // inside the edit employee pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onEditEmployee(selected_barista, confirm_edit_enabled, text_changed, prev_username, prev_password, newUsername, newPassword);
        }
    };

    @Override
    public void deleteEmployee(Barista b) {
        selected_barista = b;
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_delete_employee, null);

        // Create and show delete employee popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        delete_employee_popup = new PopupWindow(pop_up, width, height, true);
        delete_employee_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        Button confirmDeleteButton = pop_up.findViewById(R.id.btn_final_delete_employee);
        confirmDeleteButton.setOnClickListener(onConfirmDeleteButton);

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_delete_employee);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the delete employee pop up
            @Override
            public void onClick(View v) {
                delete_employee_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    View.OnClickListener onConfirmDeleteButton = new View.OnClickListener() {
        // User clicked the confirm button
        // inside the delete employee pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onDeleteEmployee(selected_barista);
        }
    };
}
