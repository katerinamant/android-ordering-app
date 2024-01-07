package com.example.softcafeengineer.view.Manager.InfoInput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.User;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;
import com.example.softcafeengineer.view.Manager.Actions.ManagerActionsActivity;


public class InfoInputActivity extends AppCompatActivity implements InfoInputView
{
    private EditText addressField, phoneNumberField, ssnField, brandField;
    private Button finishButton;
    private boolean finish_button_enabled;
    private String username, password, address, phoneNumber, ssn, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info_input);

        final InfoInputPresenter presenter = new InfoInputPresenter(this, new ManagerDAOMemory(), new CafeteriaDAOMemory(), new RevenueDAOMemory());

        // Get user from ManagerSignUpActivity
        Intent intent = getIntent();
        User user = (User) intent.getParcelableExtra("user");
        username = user.getUsername();
        password = user.getPassword();

        addressField = findViewById(R.id.edit_txt_mngr_adress_info);
        phoneNumberField = findViewById(R.id.edit_txt_mngr_phone_info);
        ssnField = findViewById(R.id.edit_txt_mngr_ssn_info);
        brandField = findViewById(R.id.edit_txt_mngr_brand_info);
        finishButton = findViewById(R.id.btn_mngr_finish_infoinput);

        // Finish button is disabled
        finish_button_enabled = false;
        finishButton.setAlpha(0.5f); // set opacity to seem disabled
        addressField.addTextChangedListener(infoWatcher);
        phoneNumberField.addTextChangedListener(infoWatcher);
        ssnField.addTextChangedListener(infoWatcher);
        brandField.addTextChangedListener(infoWatcher);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.onFinish(finish_button_enabled, username, password, address, phoneNumber, ssn, brand); };
        });
    }

    TextWatcher infoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            address = addressField.getText().toString().trim();
            phoneNumber = phoneNumberField.getText().toString();
            ssn = ssnField.getText().toString();
            brand = brandField.getText().toString().trim();
            if(!address.isEmpty() && !phoneNumber.isEmpty() && !ssn.isEmpty() && !brand.isEmpty()) {
                finishButton.setAlpha(1.0f);
                finish_button_enabled = true;
            } else {
                finishButton.setAlpha(.5f);
                finish_button_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void successfulFinish(Cafeteria cafe) {
        Intent intent = new Intent(InfoInputActivity.this, ManagerActionsActivity.class);
        intent.putExtra("cafe_brand", cafe.getBrand());
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(InfoInputActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
