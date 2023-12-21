package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Barista;

public interface BaristaDAO
{
    /**
     * Look up barista by
     * username and password
     */
    Barista find(String username, String password);

    /**
     * Save new barista
     */
    boolean save(Barista barista);

    /**
     * Delete barista
     */
    void delete(Barista barista);
}
