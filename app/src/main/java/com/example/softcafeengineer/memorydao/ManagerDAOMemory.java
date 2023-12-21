package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerDAOMemory implements ManagerDAO
{
    protected static List<User> users = new ArrayList<User>();

    public ManagerDAOMemory() { users.add(new User("katerina", "123")); }

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

    public boolean save(User user) {
        if(users.contains(user)) return false;

        // User is not in list
        // Each user must have a unique username
        for(User u : users) {
            if(u.getUsername().equalsIgnoreCase(user.getUsername())) {
                // Cannot add user due to username conflict
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public void delete(User user) {
        users.remove(user);
    }
}
