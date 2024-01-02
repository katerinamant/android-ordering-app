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

public class ManageEmployeesActivity extends AppCompatActivity implements ManageEmployeesView
{
    ManageEmployeesViewModel viewModel;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employees);

        viewModel = new ViewModelProvider(this).get(ManageEmployeesViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null){
            Intent intent = getIntent();
            String brand = intent.getStringExtra("cafe_brand");
        }
        List<Barista> employeesList = viewModel.getPresenter().getEmployeeResults();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_emloyees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EmployeeRecyclerViewAdapter(employeesList, (EmployeeRecyclerViewAdapter.ItemSelectionListener) this));

        Button add_employee = (Button) findViewById(R.id.btn_manage_employees_add);

    }
}
