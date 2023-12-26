package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.domain.Date;

public interface ManagerRevenueView
{
    /**
     * Updates monthly total
     * shown based on year and month selected
     */
    void updateMonthlyTotal(double monthTotal);

    /**
     * Updates daily total
     * shown based on year, month and day selected
     */
    void updateDailyTotal(double dayTotal);

    void showError(String title, String msg);

    void showToast(String msg);

    void enableDaySelection();

    void disableDaySelection();
}
