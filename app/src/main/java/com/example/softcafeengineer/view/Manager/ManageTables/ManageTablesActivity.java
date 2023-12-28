package com.example.softcafeengineer.view.Manager.ManageTables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Table;

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
        }
        List<Table> tableList = viewModel.getPresenter().getTableResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tables);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TableRecyclerViewAdapter(tableList, this));


        Button add_table = (Button) findViewById(R.id.btn_manage_tables_add);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        add_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_add_table, null);
                PopupWindow popupWindow = new PopupWindow(container, width, height, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public void deleteTable(Table t) {
        // doesnt work, should add pop up
        viewModel.getPresenter().deleteTable(t);
    }
}
