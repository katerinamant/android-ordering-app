package com.example.softcafeengineer.view.Manager.ManageEmployees;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        Button addEmployeeButton = findViewById(R.id.btn_manage_employees_add); // "Add new" button
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
