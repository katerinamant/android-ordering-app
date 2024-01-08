package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.InvalidDateException;

public class ManagerRevenuePresenter {
    private final ManagerRevenueView view;
    private final RevenueDAO revenueDAO;
    private final String brand;

    public ManagerRevenuePresenter(ManagerRevenueView view, String brand, RevenueDAO revenueDAO) {
        this.view = view;
        this.brand = brand;
        this.revenueDAO = revenueDAO;
    }

    public void onCalculate(boolean calculate_button_enabled, int year, int month, int day) {
        if (!calculate_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
            return;
        }
        if (!revenueDAO.containsCafeteria(this.brand)) {
            // Cafeteria is not saved in DAO
            view.showError("Unable to retrieve data.", "Please try again later.");
            return;
        }

        double monthTotal, dailyTotal;
        try {
            monthTotal = revenueDAO.getMonthTotal(this.brand, year, month);
            dailyTotal = revenueDAO.getDay(this.brand, year, month, day);
            view.onCalculation(monthTotal, dailyTotal);
        } catch (InvalidDateException e) {
            // Not possible, input is checked
            // by the TextWatcher
            // but the exception must be handled
            view.showError("Unable to retrieve data.", "Please provide valid input");
        }
    }
}
