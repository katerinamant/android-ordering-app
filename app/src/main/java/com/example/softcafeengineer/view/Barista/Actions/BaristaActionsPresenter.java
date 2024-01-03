package com.example.softcafeengineer.view.Barista.Actions;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class BaristaActionsPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private MonthlyRevenueDAO monthlyRevenueDAO;
    private BaristaActionsView view;
    private String cafe_brand, key;
    private int day;
    private List<Order> orderResults = new ArrayList<>();

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setMonthlyRevenueDAO(MonthlyRevenueDAO monthlyRevenueDAO) { this.monthlyRevenueDAO = monthlyRevenueDAO; }
    public MonthlyRevenueDAO getMonthlyRevenueDAO() { return this.monthlyRevenueDAO; }

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
        this.key = String.format("%d - %d", month, year);
    }

    public void findAll(String brand) {
        this.orderResults.clear();
        this.orderResults = activeOrdersDAO.findAll(brand);
    }

    public List<Order> getOrderResults() { return this.orderResults; }

    public void onCloseDay() {
        monthlyRevenueDAO.closeDay(this.cafe_brand, this.key, this.day);
        this.orderResults.clear();
        view.onClosedDay();
    }
}
