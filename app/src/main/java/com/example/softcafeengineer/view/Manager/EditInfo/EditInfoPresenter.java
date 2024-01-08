package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;

public class EditInfoPresenter {
    private EditInfoView view;
    private ActiveOrdersDAO activeOrdersDAO;
    private BaristaDAO baristaDAO;
    private CafeteriaDAO cafeteriaDAO;
    private MenuDAO menuDAO;
    private RevenueDAO revenueDAO;
    private TableDAO tableDAO;
    private Cafeteria cafe;

    public EditInfoPresenter(EditInfoView view, String brand, ActiveOrdersDAO activeOrdersDAO, BaristaDAO baristaDAO, CafeteriaDAO cafeteriaDAO, MenuDAO menuDAO, RevenueDAO revenueDAO, TableDAO tableDAO) {
        this.view = view;
        this.activeOrdersDAO = activeOrdersDAO;
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

    public void onFinish(boolean finish_enabled, boolean text_changed, String prev_brand, String phoneNum, String tin, String brand) {
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
            } else if (tin.length() != 9) {
                view.showToast("Invalid TIN. Must contain 9 characters.");
            } else if (!brand.equals(prev_brand) && cafeteriaDAO.exists(brand)) {
                // Brand changed and new one is taken
                view.showToast("This brand is already in use.");
            } else {
                // The user is shown a confirm changes popup
                view.validFinish();
            }
        }
    }

    public void onConfirmChanges(String prev_address, String prev_phone_number, String prev_tin, String prev_brand, String address, String phoneNum, String tin, String brand) {
        // Update Cafeteria object
        if (!address.equals(prev_address)) this.cafe.setAddress(address);
        if (!phoneNum.equals(prev_phone_number)) this.cafe.setPhoneNumber(phoneNum);
        if (!tin.equals(prev_tin)) this.cafe.setTIN(tin);
        if (!brand.equals(prev_brand)) {
            activeOrdersDAO.updateCafeteria(prev_brand, brand);
            baristaDAO.updateCafeteria(prev_brand, brand);
            cafeteriaDAO.updateCafeteria(prev_brand, brand);
            menuDAO.updateCafeteria(prev_brand, brand);
            revenueDAO.updateCafeteria(prev_brand, brand);
            tableDAO.updateCafeteria(prev_brand, brand);
        }
        view.successfulFinish(this.cafe);
    }
}
