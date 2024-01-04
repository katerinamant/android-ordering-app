package com.example.softcafeengineer.view.Barista.ManageOrder;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ManageOrderPresenter
{
    private ActiveOrdersDAO activeOrdersDAO;
    private ManageOrderView view;
    private Order order;

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO() { return this.activeOrdersDAO; }

    public void setView(ManageOrderView view) { this.view = view; }

    public double getTotalCost() {
        return this.order.getTotalCost();
    }

    public List<OrderInfo> getOrderResults(String cafe_brand, int table_number) {
        this.order = activeOrdersDAO.findInCafeteria(cafe_brand, table_number);
        return order.getOrderList();
    }
}
