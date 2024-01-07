package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.google.android.material.tabs.TabLayout;

public class EditInfoPresenter {
    private EditInfoView view;
    private CafeteriaDAO cafeteriaDAO;
    private MonthlyRevenueDAO revenueDAO;
    private BaristaDAO baristaDAO;
    private TableDAO tableDAO;
    private MenuDAO menuDAO;
    private Cafeteria cafe;

    public EditInfoPresenter(EditInfoView view, String brand, CafeteriaDAO cafeteriaDAO, MonthlyRevenueDAO revenueDAO, BaristaDAO baristaDAO, TableDAO tableDAO, MenuDAO menuDAO) {
        this.view = view;
        this.cafeteriaDAO = cafeteriaDAO;
        this.revenueDAO = revenueDAO;
        this.baristaDAO = baristaDAO;
        this.tableDAO = tableDAO;
        this.menuDAO = menuDAO;
        this.cafe = this.cafeteriaDAO.find(brand);
    }

    public Cafeteria getCafe() {
        return this.cafe;
    }

    public void onFinish(boolean finish_enabled, boolean text_changed, String prev_brand, String phoneNum, String ssn, String brand) {
        if (!finish_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if (!text_changed) {
            // Fields filled and no text changed
            view.successfulFinish(this.cafe);
        } else {
            // Fields filled and text changed
            if (phoneNum.length() != 10) {
                view.showToast("Invalid phone number. Must contain 10 characters.");
            } else if (ssn.length() != 9) {
                view.showToast("Invalid SSN. Must contain 9 characters.");
            } else if (!brand.equals(prev_brand) && cafeteriaDAO.exists(brand)) {
                // Brand changed and new one is taken
                view.showToast("This brand is already in use.");
            } else {
                // The user is shown a confirm changes popup
                view.validFinish();
            }
        }
    }

    public void onConfirmChanges(String prev_address, String prev_phone_number, String prev_ssn, String prev_brand, String address, String phoneNum, String ssn, String brand) {
        // Update Cafeteria object
        if (!address.equals(prev_address)) this.cafe.setAddress(address);
        if (!phoneNum.equals(prev_phone_number)) this.cafe.setPhoneNumber(phoneNum);
        if (!ssn.equals(prev_ssn)) this.cafe.setSSN(ssn);
        if (!brand.equals(prev_brand)) {
            cafeteriaDAO.updateCafeteria(prev_brand, brand);
            revenueDAO.updateCafeteria(prev_brand, brand);
            baristaDAO.updateCafeteria(prev_brand, brand);
            tableDAO.updateCafeteria(prev_brand, brand);
            menuDAO.updateCafeteria(prev_brand, brand);
        }
        view.successfulFinish(this.cafe);
    }
}
