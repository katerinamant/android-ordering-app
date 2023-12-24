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
            User newUser = new User(username, password);
            if (!users.save(newUser))
            {
              view.showToast("This username is already in use. Please choose a different one");
            }
        }
    }
}
