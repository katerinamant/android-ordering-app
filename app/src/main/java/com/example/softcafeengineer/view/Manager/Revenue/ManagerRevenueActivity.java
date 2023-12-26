package com.example.softcafeengineer.view.Manager.Revenue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

public class ManagerRevenueActivity extends AppCompatActivity implements ManagerRevenueView
{
    private String brand;
    private EditText yearField, monthField, dayField;
    private TextView month_output, day_output;
    private Button calculateButton;
    private boolean calculate_button_enabled;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_revenue);

        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        brand = intent.getStringExtra("cafe_brand");

        final ManagerRevenuePresenter presenter = new ManagerRevenuePresenter(this, "kafeteria", new MonthlyRevenueDAOMemory());

        yearField = findViewById(R.id.edit_txt_mngr_revenue_year);
        monthField = findViewById(R.id.edit_txt_mngr_revenue_month);
        dayField = findViewById(R.id.edit_txt_mngr_revenue_day);
        calculateButton = findViewById(R.id.btn_calculate_revenue);

        // Calculate button is disabled
        calculate_button_enabled = false;
        calculateButton.setAlpha(.5f); // set opacity to seem disabled
        yearField.addTextChangedListener(calculateWatcher);
        monthField.addTextChangedListener(calculateWatcher);
        dayField.addTextChangedListener(calculateWatcher);

        month_output = findViewById(R.id.txt_manager_revenue_month_output);
        day_output = findViewById(R.id.txt_manager_revenue_day_output);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onCalculate(calculate_button_enabled, year, month, day); }
        });
    }

    TextWatcher calculateWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String year_string = yearField.getText().toString();
            String month_string = monthField.getText().toString();
            String day_string = dayField.getText().toString();
            if(!year_string.isEmpty() && !month_string.isEmpty() && !day_string.isEmpty()) {
                year = Integer.parseInt(year_string);
                month = Integer.parseInt(month_string);
                day = Integer.parseInt(day_string);
                if(month <= 0 || month >= 13 || day <= 0 || day >= 32) {
                    calculateButton.setAlpha(.5f);
                    calculate_button_enabled = false;
                } else {
                    calculateButton.setAlpha(1.0f);
                    calculate_button_enabled = true;
                }
            } else {
                calculateButton.setAlpha(.5f);
                calculate_button_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onCalculation(double monthTotal, double dayTotal) {
        if(monthTotal == -1.0) {
            // There are no records for this month
            this.month_output.setText("no records");
            this.day_output.setText("no records");
        } else {
            this.month_output.setText(String.format("%.2f 💶", monthTotal));
            this.day_output.setText(String.format("%.2f 💶", dayTotal));
        }
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ManagerRevenueActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
