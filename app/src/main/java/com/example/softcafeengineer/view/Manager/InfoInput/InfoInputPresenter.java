package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.User;

public class InfoInputPresenter
{
    private InfoInputView view;
    private ManagerDAO users;
    private CafeteriaDAO cafes;
    private RevenueDAO revenue;

    public InfoInputPresenter(InfoInputView view, ManagerDAO users, CafeteriaDAO cafes, RevenueDAO revenue) {
        this.view = view;
        this.users = users;
        this.cafes = cafes;
        this.revenue = revenue;
    }

    public void onFinish(Boolean finish_enabled,String username, String password, String address, String phoneNum, String tin, String brand) {
        if(!finish_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        }
        else {
            if(phoneNum.length() != 10) {
                view.showToast("Invalid phone number. Must contain 10 characters.");
            } else if(tin.length() != 9) {
                view.showToast("Invalid TIN. Must contain 9 characters.");
            } else if(cafes.exists(brand)) {
                view.showToast("This brand is already in use.");
            } else {
                // Create new Cafeteria object
                Cafeteria cafe = new Cafeteria(address, phoneNum, tin, brand);
                cafes.save(cafe);
                revenue.addCafeteria(brand);

                // Retrieve user from ManagerSignUpActivity and set cafeteria attribute
                User user = users.find(username, password);
                user.setCafe(cafe);

                view.successfulFinish(cafe);
            }
        }
    }
}
