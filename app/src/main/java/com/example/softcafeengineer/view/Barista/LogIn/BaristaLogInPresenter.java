package com.example.softcafeengineer.view.Barista.LogIn;

import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInView;

public class BaristaLogInPresenter
{
    private BaristaLogInView view;

    public BaristaLogInPresenter(BaristaLogInView view) { this.view = view; }

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
