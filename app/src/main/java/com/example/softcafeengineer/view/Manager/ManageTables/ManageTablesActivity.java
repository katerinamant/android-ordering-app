package com.example.softcafeengineer.view.Manager.ManageTables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.List;

public class ManageTablesActivity extends AppCompatActivity implements ManageTablesView, TableRecyclerViewAdapter.ItemSelectionListener
{
    ManageTablesViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tables);

        viewModel = new ViewModelProvider(this).get(ManageTablesViewModel.class);
        viewModel.getPresenter().setView(this);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            String brand = intent.getStringExtra("cafe_brand");
            viewModel.getPresenter().findAll("kafeteria");
        }
        List<Table> tableList = viewModel.getPresenter().getTableResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tables);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TableRecyclerViewAdapter(tableList, this));


    }

    @Override
    public void deleteTable(Table t) {
        // doesnt work, should add pop up
        viewModel.getPresenter().deleteTable(t);
    }
}
