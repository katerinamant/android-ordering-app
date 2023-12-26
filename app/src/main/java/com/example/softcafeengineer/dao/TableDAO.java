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
     * Save new table
     */
    boolean save(Table table);

    /**
     * Delete table
     */
    void delete(Table table);
}
