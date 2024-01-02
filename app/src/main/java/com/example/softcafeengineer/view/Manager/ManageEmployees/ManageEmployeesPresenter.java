package com.example.softcafeengineer.view.Manager.ManageEmployees;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.Barista;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeesPresenter {
    private BaristaDAO baristaDAO;
    private ManagerDAO managerDAO;
    private ManageEmployeesView view;
    private String cafe_brand;
    private List<Barista> employeeResults = new ArrayList<>();

    public void setBaristaDAO(BaristaDAO baristaDAO) { this.baristaDAO = baristaDAO; }
    public BaristaDAO getBaristaDAO() { return this.baristaDAO; }

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
}
