package com.example.softcafeengineer.view.Manager.EditInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;
import com.example.softcafeengineer.view.Manager.Actions.ManagerActionsActivity;

public class EditInfoActivity extends AppCompatActivity implements EditInfoView
{
    private EditInfoPresenter presenter;
    private RelativeLayout relativeLayout;
    private PopupWindow confirm_changes_popup;
    private EditText addressField, phoneNumberField, ssnField, brandField;
    private Button finishButton;
    private boolean finish_button_enabled, text_changed;
    private String prev_address, prev_phone_number, prev_ssn, prev_brand;
    private String address, phoneNumber, ssn, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        prev_brand = intent.getStringExtra("cafe_brand");

        presenter = new EditInfoPresenter(this, prev_brand, new ActiveOrdersDAOMemory(), new BaristaDAOMemory(), new CafeteriaDAOMemory(), new MenuDAOMemory(), new MonthlyRevenueDAOMemory(), new TableDAOMemory());
        Cafeteria cafe = presenter.getCafe();

        prev_address = cafe.getAddress();
        addressField = findViewById(R.id.edit_txt_mngr_edit_address);
        addressField.setText(prev_address);
        prev_phone_number = cafe.getPhoneNumber();
        phoneNumberField = findViewById(R.id.edit_txt_mngr_edit_phone);
        phoneNumberField.setText(prev_phone_number);
        prev_ssn = cafe.getSSN();
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

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_edit_info); // activity_edit_info.xml layout
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onFinish(finish_button_enabled, text_changed, prev_brand, phoneNumber, ssn, brand); }
        });
    }

    TextWatcher infoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            address = addressField.getText().toString().trim();
            phoneNumber = phoneNumberField.getText().toString();
            ssn = ssnField.getText().toString();
            brand = brandField.getText().toString().trim();
            if(!address.isEmpty() && !phoneNumber.isEmpty() && !ssn.isEmpty() && !brand.isEmpty()) {
                finishButton.setAlpha(1.0f);
                finish_button_enabled = true;
                // Text changed when at least one field has changed
                text_changed = (!address.equals(prev_address)) || (!phoneNumber.equals(prev_phone_number) || !ssn.equals(prev_ssn) || !brand.equals(prev_brand));
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
    public void validFinish() {
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_confirm_changes, null);

        // Create and show delete table popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        confirm_changes_popup = new PopupWindow(pop_up, width, height, true);
        confirm_changes_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        Button confirmChangesButton = pop_up.findViewById(R.id.btn_final_changes);
        confirmChangesButton.setOnClickListener(onConfirmChangesButton);

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_changes);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the confirm changes pop up
            @Override
            public void onClick(View v) {
                confirm_changes_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    View.OnClickListener onConfirmChangesButton = new View.OnClickListener() {
        // User clicked the confirm button
        // inside the confirm changes table pop up
        @Override
        public void onClick(View v) {
            presenter.onConfirmChanges(prev_address, prev_phone_number, prev_ssn, prev_brand, address, phoneNumber, ssn, brand);
        }
    };

    @Override
    public void successfulFinish(Cafeteria cafe) {
        if(confirm_changes_popup != null) {
            confirm_changes_popup.dismiss();
        }
        Intent intent = new Intent(EditInfoActivity.this, ManagerActionsActivity.class);
        intent.putExtra("cafe_brand", cafe.getBrand());
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(EditInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
