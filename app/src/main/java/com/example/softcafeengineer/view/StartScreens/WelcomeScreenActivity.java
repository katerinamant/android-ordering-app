package com.example.softcafeengineer.view.StartScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInActivity;

public class WelcomeScreenActivity extends AppCompatActivity implements WelcomeScreenView
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        final WelcomeScreenPresenter presenter = new WelcomeScreenPresenter(this);

        findViewById(R.id.btn_1_1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onOrder();
            }
        });

        findViewById(R.id.btn_1_2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManagerLogIn();
            }
        });

        findViewById(R.id.btn_1_3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onEmployeeLogIn();
            }
        });

        findViewById(R.id.btn_1_4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSignUp();
            }
        });
    }

    public void startOrdering() {
        Intent intent = new Intent(WelcomeScreenActivity.this, ScanTableActivity.class);
        startActivity(intent);
    }

    @Override
    public void managerLogIn() {
        Intent intent = new Intent(WelcomeScreenActivity.this, ManagerLogInActivity.class);
        startActivity(intent);
    }

    @Override
    public void employeeLogIn() {
        Intent intent = new Intent(WelcomeScreenActivity.this, EmployeeLogInActivity.class);
        startActivity(intent);
    }

    @Override
    public void signUp() {
        Intent intent = new Intent(WelcomeScreenActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
