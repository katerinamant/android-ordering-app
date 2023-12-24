package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.User;

public interface ManagerDAO
{
    /**
     * Look up user by
     * username and password
     */
    User find(String username, String password);

    /**
     * Check to see if
     * username is already in use
     */
    boolean exists(String username);

    /**
     * Save new user
     */
    boolean save(User user);

    /**
     * Delete user
     */
    void delete(User user);
}
