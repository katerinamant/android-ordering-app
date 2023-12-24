package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

public class ManagerSignUpPresenter
{
    private ManagerSignUpView view;
    private ManagerDAO users;

    public ManagerSignUpPresenter(ManagerSignUpView view, ManagerDAO users)
    {
        this.view = view;
        this.users = users;
    }

    public void onContinue(boolean button_enabled, String username, String password)
    {
        if (!button_enabled)
        {
            view.showToast("Please fill the required fields");
        }
        else
        {
            // If the username is not being used
            if (!users.exists(username) ) {
                if (password.matches("\\w+") && password.length() < 8) {
                    User newUser = new User(username, password);
                    view.Continue();
                }
                else
                {
                    view.showToast("Your password must be more than 8 latin characters with no gaps");
                }
            }
            else
            {
                view.showToast("This username is already in use");
            }
        }
    }
}
