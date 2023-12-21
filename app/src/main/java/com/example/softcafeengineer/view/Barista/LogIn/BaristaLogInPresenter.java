package com.example.softcafeengineer.view.Barista.LogIn;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.view.Manager.LogIn.ManagerLogInView;

public class BaristaLogInPresenter
{
    private BaristaLogInView view;
    private BaristaDAO baristas;

    public BaristaLogInPresenter(BaristaLogInView view, BaristaDAO baristas) {
        this.view = view;
        this.baristas = baristas;
    }

    void onLogin(boolean login_button_enabled, String username, String password) {
        if(!login_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
        }
        else {
            // Look up credentials
            Barista result = baristas.find(username, password); //placeholder for DAO

            if(result != null) {
                // Correct credentials, change Activity
                view.successfulLogIn();
            } else {
                // Incorrect credentials, showing error
                view.showError("Log In unsuccessful.", "The credentials provided were invalid. Try again.");
            }
        }
    }
}
