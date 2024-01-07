package com.example.softcafeengineer.view.Barista.ManageOrder;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.InvalidInputException;
import com.example.softcafeengineer.domain.InvalidStatusException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ManageOrderPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private BaristaDAO baristaDAO;
    private RevenueDAO revenueDAO;
    private ManageOrderView view;
    private Barista barista;
    private Order order;

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setBaristaDAO(BaristaDAO baristaDAO) { this.baristaDAO = baristaDAO; }
    public BaristaDAO getBaristaDAO() { return this.baristaDAO; }

    public void setRevenueDAO(RevenueDAO revenueDAO) { this.revenueDAO = revenueDAO; }
    public RevenueDAO getRevenueDAO() { return this.revenueDAO; }

    public void setView(ManageOrderView view, String barista_username, String password, String cafe_brand, int table_number) {
        this.view = view;
        this.barista = baristaDAO.find(barista_username, password);
        this.order = activeOrdersDAO.findInCafeteria(cafe_brand, table_number);
    }

    public double getTotalCost() {
        this.order.calculateCost();
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
            // Add order total cost
            // to today's revenue
            String cafe_brand = this.barista.getCafe().getBrand();
            int year = this.order.getDate().getYear();
            int month = this.order.getDate().getMonth();
            int day = this.order.getDate().getDay();
            double totalCost = this.order.getTotalCost();
            revenueDAO.addToDay(cafe_brand, year, month, day, totalCost);

            // Mark order as complete
            this.order.completeOrder();

            // Permanently delete order
            activeOrdersDAO.delete(this.order);
            view.successfulCompletion();

        } catch (InvalidStatusException e)  {
            view.showError("Invalid change.", "Please choose a different status.");
        } catch (InvalidDateException e) {
            view.showError("Invalid data input.", "Please provide a valid date");
        }
    }

    public void onCanceledStatus() {
        try {
            this.order.cancelOrder();
            // Order is temporarily moved
            // to a list of cancelled orders
            // until the user acknowledges
            // the cancellation
            activeOrdersDAO.cancel(this.order);
            view.successfulCancellation();
        } catch (InvalidStatusException e) {
            view.showError("Invalid change.", "Please choose a different status.");
        }
    }

    public void onEditProductInfo(OrderInfo order_info, boolean confirm_edit_enabled, boolean text_changed, int prev_quantity, String quantity) {
        if(!confirm_edit_enabled) {
            // Field not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if (!text_changed) {
            // Field filled and no text changed
            view.successfulEdit();
        } else {
            int new_quantity = Integer.parseInt(quantity);
            if(new_quantity == 0) {
                // Remove product from order
                this.order.removeFromOrder(order_info);
                view.successfulEdit();
            } else if(prev_quantity >= new_quantity) {
                try {
                    order_info.setQuantity(new_quantity);
                    view.successfulEdit();
                } catch (InvalidInputException e) {
                    view.showError("Invalid input.", "Please provide a valid quantity.");
                }
            } else {
                view.showError("Invalid input.", String.format("Please provide a number smaller than or equal to the previous quantity (%d).", prev_quantity));
            }
        }
    }
}
