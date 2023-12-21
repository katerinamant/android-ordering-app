package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Table;
import com.google.android.material.tabs.TabLayout;

public interface TableDAO
{
    /**
     * Look up table by
     * unique id
     */
    Table find(String id);

    /**
     * Save new table
     */
    boolean save(Table table);

    /**
     * Delete table
     */
    void delete(Table table);
}
