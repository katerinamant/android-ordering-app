package com.example.softcafeengineer.view.Manager.EditInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;
import com.example.softcafeengineer.view.Manager.Actions.ManagerActionsActivity;
import com.example.softcafeengineer.view.Manager.InfoInput.InfoInputActivity;

public class EditInfoActivity extends AppCompatActivity implements EditInfoView
{
    private String intent_brand;
    private Cafeteria cafe;
    private EditText addressField, phoneNumberField, ssnField, brandField;
    private Button finishButton;
    private boolean finish_button_enabled;
    private String address, phoneNumber, ssn, new_brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        intent_brand = intent.getStringExtra("cafe_brand");

        final EditInfoPresenter presenter = new EditInfoPresenter(this, intent_brand, new CafeteriaDAOMemory(), new MonthlyRevenueDAOMemory());
        cafe = presenter.getCafe();

        addressField = findViewById(R.id.edit_txt_mngr_edit_address);
        addressField.setText(cafe.getAddress());
        phoneNumberField = findViewById(R.id.edit_txt_mngr_edit_phone);
        phoneNumberField.setText(cafe.getPhoneNumber());
        ssnField = findViewById(R.id.edit_txt_mngr_edit_ssn);
        ssnField.setText(cafe.getSSN());
        brandField = findViewById(R.id.edit_txt_mngr_edit_brand);
        brandField.setText(cafe.getBrand());
        finishButton = findViewById(R.id.btn_mngr_finish_editinfo);

        // Finish button is enabled
        finish_button_enabled = true;
        finishButton.setAlpha(1.0f);
        addressField.addTextChangedListener(infoWatcher);
        phoneNumberField.addTextChangedListener(infoWatcher);
        ssnField.addTextChangedListener(infoWatcher);
        brandField.addTextChangedListener(infoWatcher);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onFinish(finish_button_enabled, address, phoneNumber, ssn, intent_brand, new_brand); }
        });
    }

    TextWatcher infoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            address = addressField.getText().toString();
            phoneNumber = phoneNumberField.getText().toString();
            ssn = ssnField.getText().toString();
            new_brand = brandField.getText().toString();
            if(!address.isEmpty() && !phoneNumber.isEmpty() && !ssn.isEmpty() && !new_brand.isEmpty()) {
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
        Intent intent = new Intent(EditInfoActivity.this, ManagerActionsActivity.class);
        intent.putExtra("cafe_brand", cafe.getBrand());
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
