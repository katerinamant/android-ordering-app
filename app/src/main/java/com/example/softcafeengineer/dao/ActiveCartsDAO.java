package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Order;

public interface ActiveCartsDAO {
    /**
     * Current cart of orders that have
     * not been submitted yet
     * (Carts are Order objects)
     */

    /**
     * Carts can be determined
     * either by the unique id
     * of the table registered by
     * or by the combination of
     * the cafeteria brand and
     * the table number
     * (within the cafeteria)
     */

    /**
     * Find a cart based on the
     * unique id of the table
     * it was registered by
     */
    Order find(String unique_id);

    /**
     * Find a cart based on the
     * combination of cafeteria
     * and table id within the cafeteria
     */
    Order findInCafeteria(String cafeteria_brand, int table_number);

    /**
     * Add a new cart
     */
    void save(Order cart);

    /**
     * Delete cart
     */
    void delete(Order cart);
}
