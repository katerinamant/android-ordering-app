package com.example.softcafeengineer.view.Order.ScanTable;

public interface ScanTableView
{
    /**
     * When the user clicks on the "Submit" button
     * with correct id for an active order
     * they are redirected
     * to the ViewOrderStatusActivity
     */
    void showOrderStatus();

    /**
     * When the user clicks on the "Submit" button
     * with correct id they are redirected
     * to the ViewMenuActivity
     */
    void successfulSubmit(String unique_id);

    void showError(String title, String msg);

    void showToast(String msg);
}
