package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.Cafeteria;
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

    public void onFinish(Boolean finish_enabled,String username, String password, String address, String phoneNum, String ssn, String brand)
    {
        if (!finish_enabled)
        {
            view.showToast("Please fill all the required fields");
        }
        else
        {
            //an den einai swsta ta stoixeia alla ti ennooume swsta stoixeia? den mporw na provlhmatistw auth th stigmh
            Cafeteria cafe = new Cafeteria(address, phoneNum, ssn, brand);
            User user = users.find(username, password);
            user.setCafe(cafe);
            view.finish();
        }
    }
}
