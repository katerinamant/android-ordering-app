package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

public class ManagerSignUpPresenter
{
    private ManagerSignUpView view;
    private ManagerDAO users;

    public ManagerSignUpPresenter(ManagerSignUpView view, ManagerDAO users) {
        this.view = view;
        this.users = users;
    }

    public void onContinue(boolean button_enabled, String username, String password) {
        if(!button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
        }
        else {
            if(!users.exists(username)) {
                // The username is not being used
                if(password.length() >= 8) {
                    User newUser = new User(username, password);
                    users.save(newUser);
                    view.successfulContinue();
                }
                else {
                    view.showToast("Your password must have a minimum length of 8 characters.");
                }
            }
            else {
                view.showToast("This username is already in use.");
            }
        }
    }
}
