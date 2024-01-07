package com.example.softcafeengineer.view.Order.ViewCart;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.InvalidInputException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ViewCartPresenter
{
    private ViewCartView view;
    private ActiveOrdersDAO activeOrdersDAO;
    private Order order;

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setView(ViewCartView view, String unique_id) {
        this.view = view;
        this.order = activeOrdersDAO.find(unique_id);
    }

    public double getTotalCost() { return order.getTotalCost(); }

    public List<OrderInfo> getOrderResults() { return order.getOrderList(); }

    public void onSubmitOrder() {
        activeOrdersDAO.save(order);
        view.onSuccessfulSubmitOrder();
    }

    public void onEditProductInfo(OrderInfo order_info, boolean confirm_edit_enabled, boolean text_changed, int prev_quantity, String prev_comments, String quantity_string, String comments) {
        if(!confirm_edit_enabled) {
            // Field not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if (!text_changed) {
            // Field filled and no text changed
            view.successfulEdit();
        } else {
            int new_quantity = Integer.parseInt(quantity_string);
            if(new_quantity == 0) {
                // Remove product from order
                this.order.removeFromOrder(order_info);
                view.successfulEdit();
            } else {
                try {
                    order_info.setQuantity(new_quantity);
                    view.successfulEdit();
                } catch (InvalidInputException e) {
                    view.showError("Invalid input.", "Please provide a valid quantity.");
                }
            }
        }
    }
}
