package com.example.softcafeengineer.view.Manager.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory ;

public class ManagerSignUpActivity extends AppCompatActivity implements ManagerSignUpView
{
    private ManagerSignUpView viewModel;
    private Button signUp;
    private boolean signUp_enabled;
    private EditText username, password, address, phoneNumber, SSN, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_sign_up);

        final ManagerSignUpPresenter presenter = new ManagerSignUpPresenter(this, new ManagerDAOMemory());

    }
}