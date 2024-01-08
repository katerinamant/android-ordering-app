package com.example.softcafeengineer.view.Order.ViewCart;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.InvalidInputException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ViewCartPresenter {
    private ViewCartView view;
    private ActiveOrdersDAO activeOrdersDAO;
    private ActiveCartsDAO activeCartsDAO;
    private Order cart;

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) {
        this.activeOrdersDAO = activeOrdersDAO;
    }

    public ActiveOrdersDAO getActiveOrdersDAO() {
        return this.activeOrdersDAO;
    }

    public void setActiveCartsDAO(ActiveCartsDAO activeCartsDAO) {
        this.activeCartsDAO = activeCartsDAO;
    }

    public ActiveCartsDAO getActiveCartsDAO() {
        return activeCartsDAO;
    }

    public void setView(ViewCartView view, String unique_id) {
        this.view = view;
        this.cart = activeCartsDAO.find(unique_id);
    }

    public double getTotalCost() {
        cart.calculateCost();
        return cart.getTotalCost();
    }

    public List<OrderInfo> getOrderResults() {
        return cart.getOrderList();
    }

    public void onSubmitOrder() {
        activeOrdersDAO.save(cart);
        activeCartsDAO.delete(cart);
        view.onSuccessfulSubmitOrder();
    }

    public void onEditOrderInfo(OrderInfo order_info, boolean confirm_edit_enabled, boolean text_changed, int prev_quantity, String prev_comments, String quantity_string, String comments) {
        if (!confirm_edit_enabled) {
            // Field not filled, showing toast
            view.showToast("Please fill all the required fields.");
            return;
        }

        if (text_changed) {
            // Edit order info
            if (!comments.equals(prev_comments)) {
                order_info.setDescription(comments);
            }

            int new_quantity = Integer.parseInt(quantity_string);
            if (new_quantity == 0) {
                // Remove product from order
                this.cart.removeFromOrder(order_info);
            } else if (prev_quantity != new_quantity) {
                try {
                    order_info.setQuantity(new_quantity);
                } catch (InvalidInputException e) {
                    view.showError("Invalid input.", "Please provide a valid quantity.");
                    return;
                }
            }
        }
        // Successfully edited order info
        view.successfulEdit();
    }
}
