package com.example.softcafeengineer.view.Manager.ManageEmployees;

public interface ManageEmployeesView
{
    /**
     * Dismiss popup window
     * when employee is added successfully
     */
    void successfulNewEmployee();

    /**
     * Dismiss popup window
     * when employee is edited successfully
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
