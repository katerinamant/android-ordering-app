package com.example.softcafeengineer.view.Order.ScanTable;

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
import com.example.softcafeengineer.memorydao.TableDAOMemory;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

public class ScanTableActivity extends AppCompatActivity implements ScanTableView
{
    private EditText idField;
    private Button submitButton;
    private boolean submit_button_enabled;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_table);

        final ScanTablePresenter presenter = new ScanTablePresenter(this, new TableDAOMemory());

        idField = findViewById(R.id.edit_txt_table_id);
        submitButton = findViewById(R.id.btn_submit_table_id);

        // Submit button is disabled
        submit_button_enabled = false;
        submitButton.setAlpha(.5f); // set opacity to seem disabled
        idField.addTextChangedListener(idWatcher);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onSubmit(submit_button_enabled, id); }
        });
    }

    TextWatcher idWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            id = idField.getText().toString();
            if(!id.isEmpty()) {
                submitButton.setAlpha(1.0f);
                submit_button_enabled = true;
            } else {
                submitButton.setAlpha(.5f);
                submit_button_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void successfulSubmit() {
        Intent intent = new Intent(ScanTableActivity.this, WelcomeScreenActivity.class); //placeholder for ClientOrderActivity
        startActivity(intent);
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ScanTableActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
