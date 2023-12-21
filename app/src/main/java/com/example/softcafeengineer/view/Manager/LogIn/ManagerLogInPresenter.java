package com.example.softcafeengineer.view.Manager.LogIn;

import android.util.Log;

public class ManagerLogInPresenter
{
    private ManagerLogInView view;

    public ManagerLogInPresenter(ManagerLogInView view) { this.view = view; }

    void onLogin(boolean login_button_enabled) {
        if(!login_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
        }
        else {
            // Look up credentials
            boolean found = false; //placeholder for DAO
            if(found) {
                // Correct credentials, change Activity
                view.successfulLogIn();
            } else {
                // Incorrect credentials, showing error
                view.showError("Log In unsuccessful.", "The credentials provided were invalid. Try again.");
            }
        }
    }
}
