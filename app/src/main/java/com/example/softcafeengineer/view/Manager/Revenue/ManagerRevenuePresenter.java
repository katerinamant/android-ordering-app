package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;

public class ManagerRevenuePresenter
{
    private ManagerRevenueView view;
    private MonthlyRevenueDAO revenue;
    private String brand;

    public ManagerRevenuePresenter(ManagerRevenueView view, String brand, MonthlyRevenueDAO revenue) {
        this.view = view;
        this.brand = brand;
        this.revenue = revenue;
    }

    public void onValidYearAndMonth(int month, int year) {
        String key = String.format("%d-%d", month, year);

        if(this.revenue.containsMonth(this.brand, key)) {
            view.updateMonthlyTotal(this.revenue.getMonthTotal(this.brand, key));
        } else {
            view.updateMonthlyTotal(-1.0f);
        }
    }

    public void onValidDay(int month, int year, int day) {
        String key = String.format("%d-%d", month, year);

        if(this.revenue.containsMonth(this.brand, key)) {
            view.updateDailyTotal(this.revenue.getDay(this.brand, key, day));
        } else {
            // Will probably never happen since we enable day selection when the month is valid
            view.showToast("No records for this month.");
        }
    }
}
