package com.example.softcafeengineer.view.Order.ScanTable;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Status;
import com.example.softcafeengineer.domain.Table;

import java.util.Calendar;

public class ScanTablePresenter {
    private final ScanTableView view;
    private final TableDAO tables;
    private final ActiveOrdersDAO orders;

    private final ActiveCartsDAO carts;

    public ScanTablePresenter(ScanTableView view, TableDAO tables, ActiveOrdersDAO orders, ActiveCartsDAO carts) {
        this.view = view;
        this.tables = tables;
        this.orders = orders;
        this.carts = carts;
    }

    void onSubmit(boolean submit_button_enabled, String unique_id) throws InvalidDateException {
        if (!submit_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required field.");
            return;
        }

        // Look up table
        Table table = tables.find(unique_id);

        if (table == null) {
            // Incorrect id, showing error
            view.showError("Connection unsuccessful.", "The id provided was invalid. Try again.");
            return;
        }

        // Table exists

        // Check if it has an active order
        Order order = orders.find(unique_id);
        if (order != null) {
            // Table has an active order
            if (order.getOrderStatus() != Status.CANCELED) {
                // Order is active
                // show order status
                view.showOrderStatus(order.getOrderStatus());
            } else {
                // Order has been cancelled
                // Notify the user
                view.showCancelNotice();
            }
        } else {
            // Table has no active orders
            // and can submit new order
            // Initialize new Order object
            // and add to active carts

            // Get current date
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            Date date = new Date(day, month, year);
            order = new Order(date, table);
            carts.save(order);

            view.successfulSubmit(table.getQRCode());
        }
    }

    void onOkStatus() {
        view.exitStatusPopup();
    }

    public void onYesOrder(String unique_id) {
        Order order = orders.find(unique_id);
        orders.deleteCancelled(order);
        view.exitCancelPopupOnYes();
    }

    public void onNoOrder(String unique_id) {
        Order order = orders.find(unique_id);
        orders.deleteCancelled(order);
        view.exitCancelPopupOnNo();
    }
}
