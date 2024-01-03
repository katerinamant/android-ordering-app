package com.example.softcafeengineer.view.Manager.ManageEmployees;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeesPresenter {
    private BaristaDAO baristaDAO;
    private CafeteriaDAO cafeteriaDAO;
    private ManageEmployeesView view;
    private String cafe_brand;
    private List<Barista> employeeResults = new ArrayList<>();

    public void setBaristaDAO(BaristaDAO baristaDAO) { this.baristaDAO = baristaDAO; }
    public BaristaDAO getBaristaDAO() { return this.baristaDAO; }

    public void setCafeteriaDAO(CafeteriaDAO cafeteriaDAO) { this.cafeteriaDAO = cafeteriaDAO; }
    public CafeteriaDAO getCafeteriaDAO() { return this.cafeteriaDAO; }

    public void setBrand(String brand) {
        // Set cafe brand
        // and update employeeResults
        this.cafe_brand = brand;
        this.findAll(this.cafe_brand);
    }
    public String getBrand() { return this.cafe_brand; }

    public void setView(ManageEmployeesView view) { this.view = view; }

    public void findAll(String brand) {
        this.employeeResults.clear();
        this.employeeResults = baristaDAO.findAll(brand);
    }

    public List<Barista> getEmployeeResults() { return this.employeeResults; }

    public void onAddNewEmployee(boolean add_employee_enabled, String newUseranme, String newPassword) {
        if(!add_employee_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required fields.");
        }
        else {
            if(!baristaDAO.exists(newUseranme)) {
                // The username is not being used
                if(newPassword.length() >= 8) {
                    Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
                    Barista newBarista = new Barista(newUseranme, newPassword);
                    newBarista.setCafe(cafe);
                    baristaDAO.save(newBarista);
                    view.successfulNewEmployee();
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
