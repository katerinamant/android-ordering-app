package com.example.softcafeengineer.view.Manager.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory ;

public class ManagerSignUpActivity extends AppCompatActivity implements ManagerSignUpView
{
    private ManagerSignUpView viewModel;
    private Button continueButton;
    private boolean signUp_enabled;
    private EditText usernameField, passwordField, addressField, phoneNumberField, ssnField, brandField;
    private String username, password, address, brand;
    private int phoneNumber, ssn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_sign_up);

        final ManagerSignUpPresenter presenter = new ManagerSignUpPresenter(this, new ManagerDAOMemory());

        signUp_enabled = false;
        usernameField = findViewById(R.id.edit_txt_mngr_username_signup);
        passwordField = findViewById(R.id.edit_txt_mngr_password_signup);
//        addressField = findViewById(R.id.edit_txt_mngr_address_signup);
//        phoneNumberField = findViewById(R.id.edit_txt_mngr_phoneNumber_signup);
//        ssnField = findViewById(R.id.edit_txt_mngr_ssn_signup);
//        brandField = findViewById(R.id.edit_txt_mngr_brand_signup);
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
}