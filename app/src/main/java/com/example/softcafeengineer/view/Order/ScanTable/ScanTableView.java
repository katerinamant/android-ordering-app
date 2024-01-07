package com.example.softcafeengineer.view.Order.ScanTable;

import com.example.softcafeengineer.domain.Status;

public interface ScanTableView
{
    /**
     * When the user clicks on the "Submit" button
     * with correct id for an active order
     * they are shown its status
     */
    void showOrderStatus(Status orderStatus);

    /**
     * User clicked the OK button inside
     * the show status pop up
     * and they are redirected to the
     * WelcomeScreenActivity
     */
    void exitStatusPopup();

    /**
     * When the user clicks on the "Submit" button
     * with correct id for a cancelled order
     * they are shown a notice
     */
    void showCancelNotice();

    /**
     * User clicked the Yes button inside
     * the order cancelled pop up
     * and they are redirected to the
     * ViewMenuActivity
     */
    void exitCancelPopupOnYes();

    /**
     * User clicked the No button inside
     * the order cancelled pop up
     * and they are redirected to the
     * WelcomeScreenActivity
     */
    void exitCancelPopupOnNo();

    /**
     * When the user clicks on the "Submit" button
     * with correct id they are redirected
     * to the ViewMenuActivity
     */
    void successfulSubmit(String unique_id);

    void showError(String title, String msg);

    void showToast(String msg);
}
