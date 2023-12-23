package com.example.softcafeengineer.view.Manager.LogIn;

public interface ManagerLogInView
{
    /**
     * When the user clicks on the "Log In" button
     * with correct credentials
     * they are redirected to the ManagerActionsActivity
     */
    void successfulLogIn();

    void showError(String title, String msg);

    void showToast(String msg);
}
