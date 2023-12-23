package com.example.softcafeengineer.view.StartScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.view.Barista.LogIn.BaristaLogInActivity;
import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInActivity;
import com.example.softcafeengineer.view.Manager.SignUp.ManagerSignUpActivity;
import com.example.softcafeengineer.view.Order.ScanTable.ScanTableActivity;

public class WelcomeScreenActivity extends AppCompatActivity implements WelcomeScreenView
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        final WelcomeScreenPresenter presenter = new WelcomeScreenPresenter(this);

        findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onOrder();
            }
        });

        findViewById(R.id.btn_manager_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onManagerLogIn();
            }
        });

        findViewById(R.id.btn_barista_login).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onEmployeeLogIn();
            }
        });

        findViewById(R.id.btn_wlcm_sing_up).setOnClickListener(new View.OnClickListener() {
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
        Intent intent = new Intent(WelcomeScreenActivity.this, BaristaLogInActivity.class);
        startActivity(intent);
    }

    @Override
    public void signUp() {
        Intent intent = new Intent(WelcomeScreenActivity.this, ManagerSignUpActivity.class);
        startActivity(intent);
    }
}
