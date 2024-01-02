package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerDAOMemory implements ManagerDAO
{
    protected static List<User> users = new ArrayList<User>();
    protected static HashMap<String, User> username_to_manager = new HashMap<String, User>();

    @Override
    public User find(String username, String password) {
        if(username_to_manager.containsKey(username)) {
            User u = username_to_manager.get(username);
            if(u.getPassword().equals(password)) {
                // Valid username, valid password
                return u;
            } else {
                // Valid username, invalid password
                return null;
            }
        }
        // Invalid username
        return null;
    }

    public boolean exists(String username) {
      return username_to_manager.containsKey(username);
    }

    @Override
    public void save(User user) {
        // No need to check if username is in use
        // As long as we use the exists method first
        users.add(user);
        username_to_manager.put(user.getUsername(), user);
    }

    @Override
    public void delete(User user) {
        username_to_manager.remove(user.getUsername());
        users.remove(user);
    }
}
