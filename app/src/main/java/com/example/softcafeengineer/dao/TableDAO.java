package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Table;

import java.util.List;

public interface TableDAO {
    /**
     * Look up table by
     * unique id
     */
    Table find(String id);

    /**
     * Find all tables belonging
     * to a specific Cafeteria
     */
    List<Table> findAll(String cafeteria_brand);

    /**
     * Check to see if unique id
     * is already in use.
     */
    boolean exists(String unique_id);

    /**
     * Save new table
     */
    void save(Table table);

    /**
     * Delete table
     */
    void delete(Table table);

    /**
     * Changes key when table
     * changes unique id
     */
    void updateTable(String old_unique_id, String new_unique_id);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String old_brand, String new_brand);
}
