package com.example.softcafeengineer.view.Manager.ManageEmployees;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softcafeengineer.R;

public class ManageEmployeesActivity extends AppCompatActivity implements ManageEmployeesView{
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employees);
    }
}
