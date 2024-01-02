package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.revenue.MonthlyRevenues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CafeteriaDAOMemory implements CafeteriaDAO
{
    protected static List<Cafeteria> cafes = new ArrayList<Cafeteria>();
    protected static HashMap<String, Cafeteria> brand_to_cafe = new HashMap<String, Cafeteria>();

    @Override
    public Cafeteria find(String brand) {
        if(brand_to_cafe.containsKey(brand)) {
            return brand_to_cafe.get(brand);
        }
        // Cafeteria not found
        return null;
    }

    @Override
    public boolean exists(String brand) {
        return brand_to_cafe.containsKey(brand);
    }

    @Override
    public void save(Cafeteria cafe) {
        // No need to check if brand is in use
        // As long as we use the exists method first
        cafes.add(cafe);
        brand_to_cafe.put(cafe.getBrand(), cafe);
    }

    @Override
    public void delete(Cafeteria cafe) {
        brand_to_cafe.remove(cafe.getBrand());
        cafes.remove(cafe);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        Cafeteria cafe = brand_to_cafe.get(old_brand);
        brand_to_cafe.remove(old_brand);
        cafe.setBrand(new_brand);
        cafe.setBrand(new_brand);
        brand_to_cafe.put(new_brand, cafe);
    }
}
