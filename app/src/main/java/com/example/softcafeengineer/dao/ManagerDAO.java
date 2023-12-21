package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.User;

public interface ManagerDAO
{
    /**
     * Look up user by
     * username and
     * password
     */
    User find(String username, String password);

    /**
     * Save new user
     * or updated user
     */
    boolean save(User user);

    /**
     * Delete user
     */
    void delete(User user);
}
