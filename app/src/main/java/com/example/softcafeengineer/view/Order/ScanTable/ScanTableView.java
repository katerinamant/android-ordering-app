package com.example.softcafeengineer.view.Order.ScanTable;

public interface ScanTableView
{
    /**
     * When the user clicks on the "Submit" button
     * with correct id for an active order
     * they are shown its status
     */
    void showOrderStatus();

    /**
     * When the user clicks on the "Submit" button
     * with correct id for a cancelled order
     * they are shown a notice
     */
    void showCancelNotice();

    /**
     * When the user clicks on the "Submit" button
     * with correct id they are redirected
     * to the ViewMenuActivity
     */
    void successfulSubmit(String unique_id);

    void showError(String title, String msg);

    void showToast(String msg);
}
