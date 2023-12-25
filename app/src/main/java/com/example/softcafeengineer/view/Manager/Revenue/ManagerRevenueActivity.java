package com.example.softcafeengineer.view.Manager.Revenue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

public class ManagerRevenueActivity extends AppCompatActivity implements ManagerRevenueView {
    private CalendarView calendar;
    private TextView month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_revenue);

        this.showError("Choose date to view records","");

        final ManagerRevenuePresenter presenter = new ManagerRevenuePresenter(this, new MonthlyRevenueDAOMemory());

        month = findViewById(R.id.txt_manager_revenue_month);
        day = findViewById(R.id.txt_manager_revenue_day);
        calendar = findViewById(R.id.revenue_calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    Date date = new Date(dayOfMonth, month + 1, year);
                    presenter.onDateChange(date);
                } catch (InvalidDateException e) {

                }
            }
        });
    }

    @Override
    public void updateText(Date date, double monthTotal, double day) {
        this.month.setText(String.format("💵 Monthly total: %.2f", monthTotal));

        if(day == -1.0f) {
            this.day.setText("no records");
            this.showToast("No records for this day. Please choose a different date.");
        } else {
            this.day.setText(String.format("💰 Total for %s: %n%n%.2f", date.toString(), day));
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
