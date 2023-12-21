package com.example.softcafeengineer.view.Order.ScanTable;

public interface ScanTableView
{
    /**
     * When the user clicks on the "Submit" button
     * with correct id they are redirected
     * to the ClientOrderActivity
     */
    void successfulSubmit();

    void showError(String title, String msg);

    void showToast(String msg);
}
