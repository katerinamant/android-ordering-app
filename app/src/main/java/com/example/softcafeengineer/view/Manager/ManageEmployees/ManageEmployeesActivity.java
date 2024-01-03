package com.example.softcafeengineer.view.Manager.ManageEmployees;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

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

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employees);

        if (savedInstanceState == null){
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(ManageEmployeesViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrand(brand); // updates employee results
        List<Barista> employeesList = viewModel.getPresenter().getEmployeeResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_emloyees);
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

                // Create and show add table popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_employee_popup = new PopupWindow(pop_up, width, height, true);
                add_employee_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                addUsernameField = pop_up.findViewById(R.id.edit_text_add_employee_username);
                addPasswordField = pop_up.findViewById(R.id.edit_text_add_employee_password);
                // addUsernameField.addTextChangedListener(newEmployeeWatcher);
                // addPasswordField.addTextChangedListener(newEmployeeWatcher);

                addEmployeeButton = pop_up.findViewById(R.id.btn_final_add_employee);
                // addEmployeeButton.setOnClickListener(onAddEmployeeButton);
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

    // -------
    // ManageTableView implementations
    // -------

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void editEmployee(Barista b) {

    }

    @Override
    public void deleteEmployee(Barista b) {

    }
}
