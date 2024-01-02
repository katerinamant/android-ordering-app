package com.example.softcafeengineer.view.Manager.ManageTables;

import com.example.softcafeengineer.domain.Cafeteria;

public interface ManageTablesView
{
    /**
     * Dismiss popup window
     * when table is added successfully
     */
    void successfulNewTable();

    void showError(String title, String msg);

    void showToast(String msg);
}
