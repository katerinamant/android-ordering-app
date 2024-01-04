package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Order;

import java.util.List;

public interface ActiveOrdersDAO
{
    /**
     * Find an order based on the
     * unique id of the table
     * it was registered by
     */
    Order find(String unique_id);

    /**
     * Find an order based on the
     * combination of cafeteria
     * and table id within the cafeteria
     */
    Order findInCafeteria(String cafeteria_brand, int table_number);

    /**
     * Find all active orders
     * in a specific Cafeteria
     */
    List<Order> findAll(String cafeteria_brand);

    /**
     * Delete active order
     */
    void delete(Order order);
}
