package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;

public class EditInfoPresenter
{
    private EditInfoView view;
    private CafeteriaDAO cafeteriaDAO;
    private MonthlyRevenueDAO revenueDAO;
    private Cafeteria cafe;

    public EditInfoPresenter(EditInfoView view, String brand, CafeteriaDAO cafeteriaDAO, MonthlyRevenueDAO revenueDAO) {
        this.view = view;
        this.cafeteriaDAO = cafeteriaDAO;
        this.revenueDAO = revenueDAO;
        this.cafe = this.cafeteriaDAO.find(brand);
    }

    public Cafeteria getCafe() {
        return this.cafe;
    }

    public void onFinish(boolean finish_enabled, boolean text_changed, String address, String phoneNum, String ssn, String old_brand, String new_brand) {
        if(!finish_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if(!text_changed) {
            view.successfulFinish(this.cafe);
        } else {
            if(phoneNum.length() != 10) {
                view.showToast("Invalid phone number. Must contain 10 characters.");
            } else if(ssn.length() != 9) {
                view.showToast("Invalid SSN. Must contain 9 characters.");
            } else if(!new_brand.equals(old_brand) && cafeteriaDAO.exists(new_brand)) {
                view.showToast("This brand is already in use.");
            } else {
                // Update new Cafeteria object
                this.cafe.setAddress(address);
                this.cafe.setPhoneNumber(phoneNum);
                this.cafe.setSSN(ssn);
                if(!new_brand.equals(old_brand)) {
                    this.cafe.setBrand(new_brand);
                    revenueDAO.updateCafeteria(old_brand, new_brand);
                }

                view.successfulFinish(this.cafe);
            }
        }
    }
}
