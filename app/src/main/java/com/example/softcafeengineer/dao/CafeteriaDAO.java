package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Cafeteria;

public interface CafeteriaDAO
{
    /**
     * Look up cafeteria by brand
     */
    Cafeteria find(String brand);

    /**
     * Check to see if
     * brand is already in use (case sensitive)
     */
    boolean exists(String brand);

    /**
     * Save new cafeteria
     */
    void save(Cafeteria cafe);

    /**
     * Delete cafeteria
     */
    void delete(Cafeteria cafe);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String old_brand, String new_brand);
}
