package com.example.softcafeengineer.view.Order.ScanTable;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Status;
import com.example.softcafeengineer.domain.Table;

public class ScanTablePresenter
{
    private ScanTableView  view;
    private TableDAO tables;
    private ActiveOrdersDAO orders;

    public ScanTablePresenter(ScanTableView view, TableDAO tables, ActiveOrdersDAO orders) {
        this.view = view;
        this.tables = tables;
        this.orders = orders;
    }

    void onSubmit(boolean submit_button_enabled, String id) {
        if(!submit_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required field.");
        } else {
            // Look up table
            Table result = tables.find(id);

            if(result != null) {
                // Correct id, change Activity
                Order order = orders.find(id);
                if(order != null) {
                    // Table has an active order
                    if(order.getOrderStatus() != Status.CANCELED) {
                        // Order is active
                        // show order status
                        view.showOrderStatus();
                    } else {
                        view.showCancelNotice();
                    }
                } else {
                    // Table has no active orders
                    // can submit new order
                    view.successfulSubmit(result.getQRCode());
                }
            } else {
                // Incorrect id, showing error
                view.showError("Connection unsuccessful.", "The id provided was invalid. Try again.");
            }
        }
    }
}
