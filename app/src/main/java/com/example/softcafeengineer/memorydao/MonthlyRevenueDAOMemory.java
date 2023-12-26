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
        return this.revenues.get(brand).containsMonth(key);
    }

    @Override
    public double getDay(String brand, String key, int day) {
        return this.revenues.get(brand).getDay(key, day);
    }

    @Override
    public void setDay(String brand, String key, int day, double amount) {
        this.revenues.get(brand).setDay(key, day, amount);
    }

    @Override
    public double getMonthTotal(String brand, String key) {
        return this.revenues.get(brand).getMonthTotal(key);
    }
}
