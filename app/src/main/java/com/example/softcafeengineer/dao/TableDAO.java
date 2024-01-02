package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Table;

import java.util.List;

public interface TableDAO
{
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
     * Check to see if id
     * is already in use.
     */
    boolean exists(String id);

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
}
