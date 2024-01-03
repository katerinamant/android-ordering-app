package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveOrdersDAOMemory implements ActiveOrdersDAO
{
    protected static List<Order> orders = new ArrayList<Order>();
    protected static HashMap<String, Order> table_to_order = new HashMap<String, Order>(); // "table unique id" : Order
    protected static HashMap<String, ArrayList<Order>> cafeteria_to_orders = new HashMap<String, ArrayList<Order>>();

    @Override
    public Order find(String unique_id) {
        if(table_to_order.containsKey(unique_id)) {
            return table_to_order.get(unique_id);
        }
        return null;
    }

    @Override
    public List<Order> findAll(String cafeteria_brand) {
        if(cafeteria_to_orders.containsKey(cafeteria_brand)) {
            return cafeteria_to_orders.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Order order) {
        String table_unique_id = order.getTable().getQRCode();
        table_to_order.remove(table_unique_id);
        String cafe_brand = order.getTable().getCafe().getBrand();
        cafeteria_to_orders.get(cafe_brand).remove(order);
        orders.remove(order);
    }
}