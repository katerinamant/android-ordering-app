package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerDAOMemory implements ManagerDAO
{
    protected static List<User> users = new ArrayList<User>();

    @Override
    public User find(String username, String password) {
        for(User u : users) {
            if(u.getUsername().equalsIgnoreCase(username)) {
                if(u.getPassword().equals(password)) {
                    // Valid username, valid password
                    return u;
                } else {
                    // Valid username, invalid password
                    return null;
                }
            }
        }
        // Invalid username
        return null;
    }

    public boolean exists(String username) {
      for (User u : users) {
          if (u.getUsername().equalsIgnoreCase(username)) return true;
      }
      return false;
    }

    @Override
    public void save(User user) {
        // No need to check if username is in use
        // As long as we use the exists method first
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
