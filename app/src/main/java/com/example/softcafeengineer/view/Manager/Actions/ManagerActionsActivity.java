package com.example.softcafeengineer.view.Manager.Actions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

public class ManagerActionsActivity extends AppCompatActivity implements ManagerActionsView
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_actions);
        final ManagerActionsPresenter presenter = new ManagerActionsPresenter(this);
//        findViewById(R.id.btn_edit_Cinfo).setOnClickListener(v -> edit_Cinfo());
//        findViewById(R.id.btn_manage_employees).setOnClickListener(v -> manage_employees());
//        findViewById(R.id.btn_manage_tables).setOnClickListener(v -> manage_tables());
//        findViewById(R.id.btn_edit_menu).setOnClickListener(v -> edit_menu());
//        findViewById(R.id.btn_revenue).setOnClickListener(v -> revenue_breakdown());
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
        Intent intent = new Intent(ManagerActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void manage_employees(){
        Intent intent = new Intent(ManagerActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void manage_tables(){
        Intent intent = new Intent(ManagerActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void edit_menu(){
        Intent intent = new Intent(ManagerActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void revenue_breakdown(){
        Intent intent = new Intent(ManagerActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }
}
