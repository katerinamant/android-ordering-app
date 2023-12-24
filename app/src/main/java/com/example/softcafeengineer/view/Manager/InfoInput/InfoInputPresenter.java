package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;
public class InfoInputPresenter
{
    private InfoInputView view;
    private ManagerDAO users;

    public InfoInputPresenter(InfoInputView view, ManagerDAO users)
    {
        this.view = view;
        this.users = users;
    }

    public void onFinish(Boolean finish_enabled, String address, int phoneNum, int ssn, String brand)
    {

    }
}
