package com.example.softcafeengineer.view.Barista.LogIn;

public interface BaristaLogInView
{
    /**
     * When the user clicks on the "Log In" button
     * with correct credentials
     * they are redirected to the ActiveOrdersActivity
     */
    void successfulLogIn();

    void showError(String title, String msg);

    void showToast(String msg);
}
