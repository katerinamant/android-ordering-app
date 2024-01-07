package com.example.softcafeengineer.view.Barista.Actions;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class BaristaActionsPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private RevenueDAO revenueDAO;
    private BaristaActionsView view;
    private String cafe_brand;
    private int day;
    private List<Order> orderResults = new ArrayList<>();

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setRevenueDAO(RevenueDAO revenueDAO) { this.revenueDAO = revenueDAO; }
    public RevenueDAO getRevenueDAO() { return this.revenueDAO; }

    public void setBrand(String brand) {
        // Set cafe brand
        // and update tableResults
        this.cafe_brand = brand;
        this.findAll(this.cafe_brand);
    }
    public String getBrand() { return this.cafe_brand; }

    public void setView(BaristaActionsView view) { this.view = view; }

    public void setDate(int day, int month, int year) {
        this.day = day;
    }

    public void findAll(String brand) {
        this.orderResults.clear();
        this.orderResults = activeOrdersDAO.findAll(brand);
    }

    public List<Order> getOrderResults() { return this.orderResults; }

    public void onLogOutButton() {
        this.orderResults.clear();
        view.onLogOut();
    }
}
