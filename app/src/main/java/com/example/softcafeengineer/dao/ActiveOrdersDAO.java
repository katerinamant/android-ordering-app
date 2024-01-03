package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Order;

import java.util.List;

public interface ActiveOrdersDAO
{
    /**
     * Find an order based on the
     * table registered by
     */
    Order find(String unique_id);

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
