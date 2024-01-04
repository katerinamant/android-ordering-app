package com.example.softcafeengineer.view.Barista.ManageOrder;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.InvalidStatusException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class ManageOrderPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private BaristaDAO baristaDAO;
    private ManageOrderView view;
    private Barista barista;
    private Order order;

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setBaristaDAO(BaristaDAO baristaDAO) { this.baristaDAO = baristaDAO; }
    public BaristaDAO getBaristaDAO() { return this.baristaDAO; }

    public void setView(ManageOrderView view, String barista_username, String password, String cafe_brand, int table_number) {
        this.view = view;
        this.barista = baristaDAO.find(barista_username, password);
        this.order = activeOrdersDAO.findInCafeteria(cafe_brand, table_number);
    }

    public double getTotalCost() {
        return this.order.getTotalCost();
    }
    public String getOrderStatus() {
        return this.order.getOrderStatus().toString();
    }

    public List<OrderInfo> getOrderResults() { return order.getOrderList(); }

    public void onWaitingStatus() {
        view.showError("Invalid change.", "Please choose a different status.");
    }

    public void onInProgressStatus() {
        try {
            this.order.executeOrder(barista);
            view.successfulExecution();
        } catch (InvalidStatusException e)  {
            view.showError("Invalid change.", "Please choose a different status.");
        }
    }

    public void onCompletedStatus() {
        try {
            this.order.completeOrder();
            activeOrdersDAO.delete(this.order);
            view.successfulCompletion();
        } catch (InvalidStatusException e)  {
            view.showError("Invalid change.", "Please choose a different status.");
        }
    }

    public void onCanceledStatus() {
        try {
            this.order.cancelOrder();
            activeOrdersDAO.delete(this.order);
            view.successfulCancellation();
        } catch (InvalidStatusException e)  {
            view.showError("Invalid change.", "Please choose a different status.");
        }
    }
}
