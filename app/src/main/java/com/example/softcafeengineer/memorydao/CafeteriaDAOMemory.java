package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.domain.Cafeteria;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAOMemory implements CafeteriaDAO
{
    protected static List<Cafeteria> cafes = new ArrayList<Cafeteria>();
    @Override
    public Cafeteria find(String brand) {
        for(Cafeteria c : cafes) {
            if(c.getBrand().equals(brand)) return c;
        }
        // Cafeteria not found
        return null;
    }

    @Override
    public boolean exists(String brand) {
        for (Cafeteria c : cafes) {
            if (c.getBrand().equals(brand)) return true;
        }
        return false;
    }

    @Override
    public void save(Cafeteria cafe) {
        // No need to check if brand is in use
        // As long as we use the exists method first
        cafes.add(cafe);
    }

    @Override
    public void delete(Cafeteria cafe) { cafes.remove(cafe); }
}
