package com.example.softcafeengineer.view.Order.ViewCart;

import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ViewCartPresenter
{
    private ViewCartView view;

    public void setView(ViewCartView view) {
        this.view = view;
    }

    public double getTotalCost() { return 0.0; }

    public List<OrderInfo> getOrderResults() { return null; }
}
