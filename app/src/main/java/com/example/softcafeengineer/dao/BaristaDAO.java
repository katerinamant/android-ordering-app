package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Barista;

import java.util.List;

public interface BaristaDAO
{
    /**
     * Look up barista by
     * username and password
     */
    Barista find(String username, String password);

    /**
     * Find all employees working
     * in a specific Cafeteria
     */
    List<Barista> findAll(String cafeteria_brand);

    /**
     * Check to see if
     * username is already in use.
     * We only allow lower latin characters on input.
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

    /**
     * Changes key when barista
     * changes username
     */
    void updateBarista(String old_username, String new_username);
}
