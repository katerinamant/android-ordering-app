package com.example.softcafeengineer.view.Manager.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory ;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;
import com.example.softcafeengineer.view.Manager.InfoInput.InfoInputActivity;

public class ManagerSignUpActivity extends AppCompatActivity implements ManagerSignUpView
{
    private ManagerSignUpView viewModel;
    private Button continueButton;
    private boolean signUp_enabled;
    private EditText usernameField, passwordField;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_sign_up);

        final ManagerSignUpPresenter presenter = new ManagerSignUpPresenter(this, new ManagerDAOMemory());

        signUp_enabled = false;
        usernameField = findViewById(R.id.edit_txt_mngr_username_signup);
        passwordField = findViewById(R.id.edit_txt_mngr_password_signup);
        continueButton = findViewById(R.id.edit_txt_mngr_continue_signup);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {presenter.onContinue(signUp_enabled, username, password);};
        });
    }

    @Override
    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Continue()
    {
        Intent intent = new Intent(ManagerSignUpActivity.this, InfoInputActivity.class);
        startActivity(intent);
    }
}