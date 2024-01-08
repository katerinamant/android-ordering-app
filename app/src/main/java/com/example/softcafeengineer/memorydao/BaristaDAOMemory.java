package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaristaDAOMemory implements BaristaDAO {
    protected static List<Barista> baristas = new ArrayList<Barista>();
    protected static HashMap<String, Barista> username_to_barista = new HashMap<String, Barista>();
    protected static HashMap<String, ArrayList<Barista>> cafeteria_to_baristas = new HashMap<String, ArrayList<Barista>>();

    @Override
    public Barista find(String username, String password) {
        if (username_to_barista.containsKey(username)) {
            Barista b = username_to_barista.get(username);
            if (b.getPassword().equals(password)) {
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

    @Override
    public List<Barista> findAll(String cafeteria_brand) {
        if (cafeteria_to_baristas.containsKey(cafeteria_brand)) {
            return cafeteria_to_baristas.get(cafeteria_brand);
        }
        return new ArrayList<>();
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

        String brand_key = barista.getCafe().getBrand();
        if (cafeteria_to_baristas.containsKey(brand_key)) {
            cafeteria_to_baristas.get(brand_key).add(barista);
        } else {
            ArrayList<Barista> baristas = new ArrayList<Barista>();
            baristas.add(barista);
            cafeteria_to_baristas.put(brand_key, baristas);
        }
    }

    @Override
    public void delete(Barista barista) {
        username_to_barista.remove(barista.getUsername());
        cafeteria_to_baristas.get(barista.getCafe().getBrand()).remove(barista);
        baristas.remove(barista);
    }

    @Override
    public void updateBarista(String old_username, String new_username) {
        Barista barista = username_to_barista.get(old_username);
        username_to_barista.remove(old_username);
        barista.setUsername(new_username);
        username_to_barista.put(new_username, barista);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        if (cafeteria_to_baristas.containsKey(old_brand)) {
            ArrayList<Barista> baristas = cafeteria_to_baristas.get(old_brand);
            cafeteria_to_baristas.remove(old_brand);
            cafeteria_to_baristas.put(new_brand, baristas);
        } else {
            ArrayList<Barista> baristas = new ArrayList<Barista>();
            cafeteria_to_baristas.put(new_brand, baristas);
        }
    }
}
