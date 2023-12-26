package com.example.softcafeengineer.view.Manager.Revenue;

public interface ManagerRevenueView
{
    /**
     * Updates monthly and daily total
     * shown based on year, month and day selected
     */
    void onCalculation(double monthTotal, double dayTotal);

    void showError(String title, String msg);

    void showToast(String msg);

}
