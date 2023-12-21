package com.example.softcafeengineer.view.Barista.LogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInActivity;
import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInPresenter;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

public class BaristaLogInActivity extends AppCompatActivity implements BaristaLogInView
{
    private EditText usernameField, passwordField;
    private Button loginButton;
    private boolean login_button_enabled;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barista_log_in);

        final BaristaLogInPresenter presenter = new BaristaLogInPresenter(this, new BaristaDAOMemory());

        usernameField = findViewById(R.id.edit_txt_4_1);
        passwordField = findViewById(R.id.edit_txt_4_2);
        loginButton = findViewById(R.id.btn_4_1);

        // Login button is disabled
        login_button_enabled = false;
        loginButton.setAlpha(.5f); // set opacity to seem disabled
        usernameField.addTextChangedListener(loginWatcher);
        passwordField.addTextChangedListener(loginWatcher);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.onLogin(login_button_enabled, username, password); }
        });
    }

    TextWatcher loginWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            username = usernameField.getText().toString();
            password = passwordField.getText().toString();
            if(!username.isEmpty() && !password.isEmpty()) {
                loginButton.setAlpha(1.0f);
                login_button_enabled = true;
            } else {
                loginButton.setAlpha(.5f);
                login_button_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void successfulLogIn() {
        Intent intent = new Intent(BaristaLogInActivity.this, WelcomeScreenActivity.class); //placeholder for ActiveOrdersActivity
        startActivity(intent);
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(BaristaLogInActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
