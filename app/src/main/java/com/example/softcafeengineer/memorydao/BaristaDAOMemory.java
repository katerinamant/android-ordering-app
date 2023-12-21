package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;

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

    @Override
    public boolean save(Barista barista) {
        if(baristas.contains(barista)) return false;

        // Barista is not in list
        // Each barista must have a unique username
        for(Barista b : baristas) {
            if(b.getUsername().equalsIgnoreCase(barista.getUsername())) {
                // Cannot add barista due to username conflict
                return false;
            }
        }
        baristas.add(barista);
        return true;
    }

    @Override
    public void delete(Barista barista) {
        baristas.remove(barista);
    }
}
