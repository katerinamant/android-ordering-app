package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.List;

public class BaristaDAOMemory implements BaristaDAO
{
    protected static List<Barista> baristas = new ArrayList<Barista>();

    @Override
    public Barista find(String username, String password) {
        for(Barista b : baristas) {
            if(b.getUsername().equalsIgnoreCase(username)) {
                if(b.getPassword().equals(password)) {
                    // Valid username, valid password
                    return b;
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
        for (Barista b : baristas) {
            if (b.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    @Override
    public void save(Barista barista) {
        // No need to check if username is in use
        // As long as we use the exists method first
        baristas.add(barista);
    }

    @Override
    public void delete(Barista barista) {
        baristas.remove(barista);
    }
}
