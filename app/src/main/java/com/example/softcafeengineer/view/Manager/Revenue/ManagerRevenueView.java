package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.domain.Date;

public interface ManagerRevenueView
{
    /**
     * Updates information
     * shown based on day selected
     */
    void updateText(Date date, double monthTotal, double day);

    void showError(String title, String msg);

    void showToast(String msg);
}
