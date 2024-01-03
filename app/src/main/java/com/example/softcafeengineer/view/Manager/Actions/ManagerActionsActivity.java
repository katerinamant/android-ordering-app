package com.example.softcafeengineer.view.Manager.Actions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.view.Manager.EditInfo.EditInfoActivity;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuActivity;
import com.example.softcafeengineer.view.Manager.ManageEmployees.ManageEmployeesActivity;
import com.example.softcafeengineer.view.Manager.ManageTables.ManageTablesActivity;
import com.example.softcafeengineer.view.Manager.Revenue.ManagerRevenueActivity;

public class ManagerActionsActivity extends AppCompatActivity implements ManagerActionsView
{
    private String brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_actions);

        final ManagerActionsPresenter presenter = new ManagerActionsPresenter(this);

        TextView header = findViewById(R.id.txt_manager_activity_header);
        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        brand = intent.getStringExtra("cafe_brand");
        header.setText(String.format("Welcome back, %s!", brand));

        findViewById(R.id.btn_edit_Cinfo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onEditCInfo();
            }
        });

        findViewById(R.id.btn_manage_employees).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageEmployees();
            }
        });

        findViewById(R.id.btn_manage_tables).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManageTables();
            }
        });

        findViewById(R.id.btn_edit_menu).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onEditMenu();
            }
        });

        findViewById(R.id.btn_revenue).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onRevenueBreakdown();
            }
        });
    }

    @Override
    public void edit_Cinfo(){
        Intent intent = new Intent(ManagerActionsActivity.this, EditInfoActivity.class);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void manage_employees(){
        Intent intent = new Intent(ManagerActionsActivity.this, ManageEmployeesActivity.class); // placeholder
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void manage_tables(){
        Intent intent = new Intent(ManagerActionsActivity.this, ManageTablesActivity.class);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void edit_menu(){
        Intent intent = new Intent(ManagerActionsActivity.this, EditMenuActivity.class); // placeholder
        startActivity(intent);
    }

    @Override
    public void revenue_breakdown(){
        Intent intent = new Intent(ManagerActionsActivity.this, ManagerRevenueActivity.class);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }
}
