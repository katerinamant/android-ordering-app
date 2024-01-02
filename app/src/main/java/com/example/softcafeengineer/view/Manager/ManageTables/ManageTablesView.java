package com.example.softcafeengineer.view.Manager.ManageTables;

public interface ManageTablesView
{
    /**
     * Dismiss popup window
     * when table is added successfully
     */
    void successfulNewTable();

    /**
     * Dismiss popup window
     * when table is edited successfully
     */
    void successfulEdit();

    /**
     * Dismiss popup window
     * when table is deleted
     */
    void successfulDelete();

    void showError(String title, String msg);

    void showToast(String msg);
}
