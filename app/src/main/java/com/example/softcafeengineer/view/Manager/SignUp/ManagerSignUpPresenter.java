package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.dao.ManagerDAO;
public class ManagerSignUpPresenter {
    private ManagerSignUpView view;
    private ManagerDAO users;

    public ManagerSignUpPresenter(ManagerSignUpView view, ManagerDAO users)
    {
        this.view = view;
        this.users = users;


    }
}
