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
     * Check to see if
     * username is already in use (ignore case)
     */
    boolean exists(String username);

    /**
     * Save new barista
     */
    void save(Barista barista);

    /**
     * Delete barista
     */
    void delete(Barista barista);
}
