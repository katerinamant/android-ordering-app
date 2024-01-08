package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveCartsDAOMemory implements ActiveCartsDAO {
    protected static List<Order> carts = new ArrayList<Order>();
    protected static HashMap<String, Order> table_to_cart = new HashMap<String, Order>();


    @Override
    public Order find(String unique_id) {
        if (table_to_cart.containsKey(unique_id)) {
            return table_to_cart.get(unique_id);
        }
        return null;
    }

    @Override
    public Order findInCafeteria(String cafeteria_brand, int table_number) {
        for (Order cart : carts) {
            String brand = cart.getTable().getCafe().getBrand();
            int number = cart.getTable().getId();
            if (brand.equals(cafeteria_brand) && number == table_number) {
                return cart;
            }
        }
        return null;
    }

    @Override
    public void save(Order cart) {
        Table table = cart.getTable();
        carts.add(cart);
        table_to_cart.put(table.getQRCode(), cart);
    }

    @Override
    public void delete(Order cart) {
        String table_unique_id = cart.getTable().getQRCode();
        table_to_cart.remove(table_unique_id);
        carts.remove(cart);
    }
}
