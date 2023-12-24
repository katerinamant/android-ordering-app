package com.example.softcafeengineer.view.Manager.InfoInput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.view.Manager.SignUp.ManagerSignUpActivity;
import com.example.softcafeengineer.view.Manager.Actions.ManagerActionsActivity;


public class InfoInputActivity extends AppCompatActivity implements InfoInputView
{
    private EditText addressField, phoneNumberField, ssnField, brandField;
    private Button finishButton;
    private String address, brand;
    private int phoneNumber, ssn;
    private boolean finish_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info_input);

        final InfoInputPresenter presenter = new InfoInputPresenter(this, new ManagerDAOMemory());

        finish_enabled = false;

        addressField = findViewById(R.id.edit_txt_mngr_address_infoinput);
        phoneNumberField = findViewById(R.id.edit_txt_mngr_phoneNumber_infoinput);
        ssnField = findViewById(R.id.edit_txt_mngr_ssn_infoinput);
        brandField = findViewById(R.id.edit_txt_mngr_brand_infoinput);
        finishButton = findViewById(R.id.edit_txt_mngr_finish_infoinput);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {presenter.onFinish(finish_enabled, address, phoneNumber, ssn, brand);};
        });
    }

    @Override
    public void finish()
    {
        Intent intent = new Intent(InfoInputActivity.this, ManagerActionsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String msg)
    {

    }
}
