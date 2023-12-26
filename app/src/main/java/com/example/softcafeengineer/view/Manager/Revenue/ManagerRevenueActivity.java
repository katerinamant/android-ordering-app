package com.example.softcafeengineer.view.Manager.Revenue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

public class ManagerRevenueActivity extends AppCompatActivity implements ManagerRevenueView
{
    private String brand;
    private EditText yearField, monthField, dayField;
    private TextView month_output, day_prompt, day_output_title, day_output;
    private boolean valid_year, valid_month, day_selection_enabled;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_revenue);

        // Get cafe brand from previous Activity
        Intent intent = getIntent();
        brand = intent.getStringExtra("cafe_brand");

        final ManagerRevenuePresenter presenter = new ManagerRevenuePresenter(this, this.brand, new MonthlyRevenueDAOMemory());

        yearField = findViewById(R.id.edit_txt_mngr_revenue_year);
        monthField = findViewById(R.id.edit_txt_mngr_revenue_month);
        month_output = findViewById(R.id.txt_manager_revenue_month_output);
        valid_year = valid_month = false;
        yearField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String year_string = yearField.getText().toString();
                if(!year_string.isEmpty()) {
                    year = Integer.parseInt(year_string);
                    valid_year = true;
                    if(valid_month) presenter.onValidYearAndMonth(month, year);
                } else {
                    valid_year = false;
                    ManagerRevenueActivity.this.disableDaySelection();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        monthField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String month_string = monthField.getText().toString();
                if(!month_string.isEmpty()) {
                    month = Integer.parseInt(month_string);
                    if(month >= 1 && month <= 12) {
                        valid_month = true;
                        if(valid_year) presenter.onValidYearAndMonth(month, year);
                    } else {
                        valid_month = false;
                        ManagerRevenueActivity.this.showToast("Invalid month input");
                        ManagerRevenueActivity.this.disableDaySelection();
                    }
                } else {
                    valid_month = false;
                    ManagerRevenueActivity.this.disableDaySelection();
                }
            }
        });

        day_prompt = findViewById(R.id.txt_manager_revenue_choose_day);
        dayField = findViewById(R.id.edit_txt_mngr_revenue_day);
        day_output_title = findViewById(R.id.txt_manager_revenue_daily_total);
        day_output = findViewById(R.id.txt_manager_revenue_day_output);
        // Day selection is disabled
        this.disableDaySelection();
        dayField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String day_string = dayField.getText().toString();
                if(!day_string.isEmpty()) {
                    day = Integer.parseInt(day_string);
                    if(day >= 1 && day <= 31) {
                        presenter.onValidDay(month, year, day);
                    } else {
                        ManagerRevenueActivity.this.showToast("Invalid day input");
                    }
                } else {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void updateMonthlyTotal(double monthTotal) {
        if(monthTotal == -1.0) {
            this.month_output.setText("no records");
            this.disableDaySelection();
        } else {
            this.month_output.setText(String.format("%.2f 💶", monthTotal));
            this.enableDaySelection();
        }
    }

    @Override
    public void updateDailyTotal(double dayTotal) {
        if(dayTotal == -1.0) {
            this.day_output.setText("no records");
        } else {
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

    @Override
    public void enableDaySelection() {
        day_selection_enabled = true;
        day_prompt.setAlpha(1.0f);
        dayField.setAlpha(1.0f);
        day_output_title.setAlpha(1.0f);
        day_output.setAlpha(1.0f);
    }

    @Override
    public void disableDaySelection() {
        day_selection_enabled = false;
        day_prompt.setAlpha(0.0f);
        dayField.setAlpha(0.0f);
        day_output_title.setAlpha(0.0f);
        day_output.setAlpha(0.0f);
    }
}
