package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Date;

public class ManagerRevenuePresenter
{
    private ManagerRevenueView view;
    private MonthlyRevenueDAO revenue;

    public ManagerRevenuePresenter(ManagerRevenueView view, MonthlyRevenueDAO revenue) {
        this.view = view;
        this.revenue = revenue;
    }

    void onDateChange(Date date) {
        String key = String.format("%d-%d", date.getMonth(), date.getYear());
        double day_amount = -1.0;

        if(this.revenue.containsMonth(key)) {
            view.updateText(date, this.revenue.getMonthTotal(key), this.revenue.getDay(key, date.getDay()));
        } else {
            view.showError("No records for this month.", "Please choose a different date.");
        }
    }
}
