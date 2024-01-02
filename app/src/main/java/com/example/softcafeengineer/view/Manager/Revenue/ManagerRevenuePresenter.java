package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.dao.MonthlyRevenueDAO;

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

    public void onCalculate(boolean calculate_button_enabled, int year, int month, int day) {
        if (!calculate_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
        } else {
            if (!revenue.containsCafeteria(this.brand)) {
                // Cafeteria is not saved in DAO
                view.showError("Unable to retrieve data.", "Please try again later.");
            } else {
                String key = String.format("%d-%d", month, year);
                if (this.revenue.containsMonth(this.brand, key)) {
                    view.onCalculation(this.revenue.getMonthTotal(this.brand, key), this.revenue.getDay(this.brand, key, day));
                } else {
                    // There are no records for this month
                    view.onCalculation(-1.0, -1.0);
                }
            }
        }
    }
}
