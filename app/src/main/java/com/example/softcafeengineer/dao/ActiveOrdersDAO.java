package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Order;

import java.util.List;

public interface ActiveOrdersDAO
{
    /**
     * Orders can be determined
     * either by the unique id
     * of the table registered by
     * or by the combination of
     * the cafeteria brand and
     * the table number
     * (within the cafeteria)
     */

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
     * Add a newly submitted order
     */
    void save(Order order);

    /**
     * Move active order
     * to temporary list
     * of cancelled orders
     * that have not been
     * acknowledged yet
     */
    void cancel(Order order);

    /**
     * Delete active order
     */
    void delete(Order order);

    /**
     * Permanently delete
     * cancelled order
     */
    void deleteCancelled(Order order);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String prevBrand, String brand);
}
