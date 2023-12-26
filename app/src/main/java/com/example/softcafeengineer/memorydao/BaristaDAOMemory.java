package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaristaDAOMemory implements BaristaDAO
{
    protected static List<Barista> baristas = new ArrayList<Barista>();
    protected static HashMap<String, Barista> username_to_barista = new HashMap<String, Barista>();

    @Override
    public Barista find(String username, String password) {
        if(username_to_barista.containsKey(username)) {
            Barista b = username_to_barista.get(username);
            if(b.getPassword().equals(password)) {
                // Valid username, valid password
                return b;
            } else {
                // Valid username, invalid password
                return null;
            }
        }
        // Invalid username
        return null;
    }

    public boolean exists(String username) {
        return username_to_barista.containsKey(username);
    }

    @Override
    public void save(Barista barista) {
        // No need to check if username is in use
        // As long as we use the exists method first
        baristas.add(barista);
        username_to_barista.put(barista.getUsername(), barista);
    }

    @Override
    public void delete(Barista barista) {
        username_to_barista.remove(barista.getUsername());
        baristas.remove(barista);
    }
}
