package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.revenue.MonthlyRevenues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MonthlyRevenueDAOMemory implements MonthlyRevenueDAO
{
    private HashMap<String, MonthlyRevenues> revenues = new HashMap<String, MonthlyRevenues>(); // "cafeteria brand" : [Monthly Revenues obj]

    @Override
    public boolean containsCafeteria(String brand) {
        return this.revenues.containsKey(brand);
    }

    @Override
    public void addCafeteria(String brand) {
        this.revenues.put(brand, new MonthlyRevenues(brand));
    }

    @Override
    public boolean containsMonth(String brand, String key) {
        // No need to check if it contains this cafeteria
        // As long as we use containsCafeteria first.
        return this.revenues.get(brand).containsMonth(key);
    }

    @Override
    public double getDay(String brand, String key, int day) {
        // No need to check if it contains this cafeteria
        // As long as we use containsCafeteria first.
        return this.revenues.get(brand).getDay(key, day);
    }

    @Override
    public void setDay(String brand, String key, int day, double amount) {
        // No need to check if it contains this cafeteria
        // As long as we use containsCafeteria first.
        this.revenues.get(brand).setDay(key, day, amount);
    }

    @Override
    public double getMonthTotal(String brand, String key) {
        // No need to check if it contains this cafeteria
        // As long as we use containsCafeteria first.
        return this.revenues.get(brand).getMonthTotal(key);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        MonthlyRevenues obj = this.revenues.get(old_brand);
        this.revenues.remove(old_brand);
        obj.setCafeBrand(new_brand);
        this.revenues.put(new_brand, obj);
    }
}
