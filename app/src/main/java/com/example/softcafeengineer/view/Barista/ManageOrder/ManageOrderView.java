package com.example.softcafeengineer.view.Barista.ManageOrder;

public interface ManageOrderView
{
    /**
     * Barista successfully changed
     * the order's status to IN_PROGRESS
     */
    void successfulExecution();

    /**
     * Barista successfully changed
     * the order's status to COMPLETED
     */
    void successfulCompletion();

    /**
     * Barista successfully changed
     * the order's status to CANCELED
     */
    void successfulCancellation();

    void showError(String title, String msg);
}
