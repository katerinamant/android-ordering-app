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

public class EditInfoActivity extends AppCompatActivity implements EditInfoView
{
    private Cafeteria cafe;
    private EditText addressField, phoneNumberField, ssnField, brandField;
    private Button finishButton;
    private boolean finish_button_enabled, text_changed;
    private String address, phoneNumber, ssn, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        String prev_brand = intent.getStringExtra("cafe_brand");

        final EditInfoPresenter presenter = new EditInfoPresenter(this, prev_brand, new CafeteriaDAOMemory(), new MonthlyRevenueDAOMemory());
        cafe = presenter.getCafe();

        String prev_address = cafe.getAddress();
        addressField = findViewById(R.id.edit_txt_mngr_edit_address);
        addressField.setText(prev_address);
        String prev_phone_number = cafe.getPhoneNumber();
        phoneNumberField = findViewById(R.id.edit_txt_mngr_edit_phone);
        phoneNumberField.setText(prev_phone_number);
        String prev_ssn = cafe.getSSN();
        ssnField = findViewById(R.id.edit_txt_mngr_edit_ssn);
        ssnField.setText(prev_ssn);
        brandField = findViewById(R.id.edit_txt_mngr_edit_brand);
        brandField.setText(prev_brand);

        finishButton = findViewById(R.id.btn_mngr_finish_editinfo);
        text_changed = false;

        // Finish button is enabled
        finish_button_enabled = true;
        finishButton.setAlpha(1.0f);
        addressField.addTextChangedListener(infoWatcher);
        phoneNumberField.addTextChangedListener(infoWatcher);
        ssnField.addTextChangedListener(infoWatcher);
        brandField.addTextChangedListener(infoWatcher);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onFinish(finish_button_enabled, text_changed, prev_address, prev_phone_number, prev_ssn, prev_brand, address, phoneNumber, ssn, brand); }
        });
    }

    TextWatcher infoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            text_changed = true;
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
        Intent intent = new Intent(EditInfoActivity.this, ManagerActionsActivity.class);
        intent.putExtra("cafe_brand", cafe.getBrand());
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
