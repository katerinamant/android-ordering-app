package com.example.softcafeengineer.view.Order.ViewCart;

public interface ViewCartView
{
    /**
     * User successfully
     * submitted their order
     * and they are redirected
     * to WelcomeScreenActivity
     */
    void onSuccessfulSubmitOrder();

    /**
     * User successfully edited
     * an order info from the order
     */
    void successfulEdit();

    void showError(String title, String msg);

    void showToast(String msg);
}
