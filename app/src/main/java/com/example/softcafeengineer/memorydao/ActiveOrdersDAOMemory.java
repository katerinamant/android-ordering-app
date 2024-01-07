package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveOrdersDAOMemory implements ActiveOrdersDAO
{
    protected static List<Order> orders = new ArrayList<Order>();
    protected static HashMap<String, Order> table_to_order = new HashMap<String, Order>(); // "table unique id" : Order
    protected static HashMap<String, ArrayList<Order>> cafeteria_to_orders = new HashMap<String, ArrayList<Order>>();
    protected static List<Order> temp_cancelled_orders = new ArrayList<>();

    @Override
    public Order find(String unique_id) {
        if(table_to_order.containsKey(unique_id)) {
            return table_to_order.get(unique_id);
        }
        return null;
    }

    @Override
    public Order findInCafeteria(String cafeteria_brand, int table_number) {
        if(cafeteria_to_orders.containsKey(cafeteria_brand)) {
            ArrayList<Order> current_active_orders = cafeteria_to_orders.get(cafeteria_brand);
            for(Order order : current_active_orders) {
                if(order.getTable().getId() == table_number) {
                    return order;
                }
            }

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
    public void save(Order order) {
        Table table = order.getTable();
        Cafeteria cafe = table.getCafe();
        orders.add(order);
        table_to_order.put(table.getQRCode(), order);
        if(cafeteria_to_orders.containsKey(cafe.getBrand())) {
            cafeteria_to_orders.get(cafe.getBrand()).add(order);
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(order);
            cafeteria_to_orders.put(cafe.getBrand(), orders);
        }
    }

    @Override
    public void cancel(Order order) {
        String table_unique_id = order.getTable().getQRCode();
        table_to_order.remove(table_unique_id);
        String cafe_brand = order.getTable().getCafe().getBrand();
        cafeteria_to_orders.get(cafe_brand).remove(order);
        orders.remove(order);
        temp_cancelled_orders.add(order);
    }

    @Override
    public void delete(Order order) {
        String table_unique_id = order.getTable().getQRCode();
        table_to_order.remove(table_unique_id);
        String cafe_brand = order.getTable().getCafe().getBrand();
        cafeteria_to_orders.get(cafe_brand).remove(order);
        orders.remove(order);
    }

    @Override
    public void deleteCancelled(Order order) {
        temp_cancelled_orders.remove(order);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        if(cafeteria_to_orders.containsKey(old_brand)) {
            ArrayList<Order> orders  = cafeteria_to_orders.get(old_brand);
            cafeteria_to_orders.remove(old_brand);
            cafeteria_to_orders.put(new_brand, orders);
        } else {
            ArrayList<Order> orders = new ArrayList<Order>();
            cafeteria_to_orders.put(new_brand, orders);
        }
    }
}
