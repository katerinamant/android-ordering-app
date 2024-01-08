package com.example.softcafeengineer.view.Manager.LogIn;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

public class ManagerLogInPresenter {
    private final ManagerLogInView view;
    private final ManagerDAO users;

    public ManagerLogInPresenter(ManagerLogInView view, ManagerDAO users) {
        this.view = view;
        this.users = users;
    }

    void onLogin(boolean login_button_enabled, String username, String password) {
        if (!login_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
            return;
        }

        // Look up credentials
        User result = users.find(username, password);

        if (result != null) {
            // Correct credentials, change Activity
            view.successfulLogIn(result.getCafe());
        } else {
            // Incorrect credentials, showing error
            view.showError("Log In unsuccessful.", "The credentials provided were invalid. Try again.");
        }
    }
}
