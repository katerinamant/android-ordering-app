package com.example.softcafeengineer.view.Barista.Actions;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Status;

import java.util.ArrayList;
import java.util.List;

public class BaristaActionsPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private RevenueDAO revenueDAO;
    private BaristaActionsView view;
    private String cafe_brand;
    private String barista_username;
    private int day;
    private List<Order> orderResults = new ArrayList<>();

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setRevenueDAO(RevenueDAO revenueDAO) { this.revenueDAO = revenueDAO; }
    public RevenueDAO getRevenueDAO() { return this.revenueDAO; }

    public void setBrand(String brand) {
        // Set cafe brand
        // and update orderResults
        this.cafe_brand = brand;
        this.findAll(this.cafe_brand);
    }
    public String getBrand() { return this.cafe_brand; }

    public void setBaristaUsername(String username) { this.barista_username = username; }
    public String getBaristaUsername() { return this.barista_username; }

    public void setView(BaristaActionsView view) { this.view = view; }

    public void setDate(int day, int month, int year) {
        this.day = day;
    }

    public void findAll(String brand) {
        this.orderResults.clear();

        List<Order> all_orders = activeOrdersDAO.findAll(brand);
        for (Order order : all_orders) {
            // The barista has access
            // only to orders with status WAITING
            // or with status IN_PROGRESS that
            // he has been assigned to
            Status status = order.getOrderStatus();
            Barista barista = order.getBarista();

            if(status == Status.CANCELED) continue;

            if(status == Status.WAITING) {
                this.orderResults.add(order);
                continue;
            }

            // Barista is only null
            // when the order has not been assigned
            String barista_username = barista.getUsername();
            if(status == Status.IN_PROGRESS && barista_username.equals((this.barista_username))) {
                this.orderResults.add(order);
            }
        }
    }

    public List<Order> getOrderResults() { return this.orderResults; }

    public void onLogOutButton() {
        view.onLogOut();
    }
}
