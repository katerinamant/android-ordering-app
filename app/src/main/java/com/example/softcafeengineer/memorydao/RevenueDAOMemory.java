package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.revenue.Revenues;

import java.util.HashMap;

public class RevenueDAOMemory implements RevenueDAO {
    protected static HashMap<String, Revenues> revenues = new HashMap<String, Revenues>(); // "cafeteria brand" : Revenues obj

    @Override
    public void addCafeteria(String brand) {
        revenues.put(brand, new Revenues(brand));
    }

    @Override
    public boolean containsCafeteria(String brand) {
        return revenues.containsKey(brand);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        Revenues revenues_of_cafeteria = revenues.get(old_brand);
        revenues.remove(old_brand);
        revenues.put(new_brand, revenues_of_cafeteria);
    }

    @Override
    public double getDay(String brand, int year, int month, int day) throws InvalidDateException {
        try {
            Revenues revenues_of_cafeteria = revenues.get(brand);
            return revenues_of_cafeteria.getDay(year, month, day);
        } catch (InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }

    @Override
    public double getMonthTotal(String brand, int year, int month) throws InvalidDateException {
        try {
            Revenues revenues_of_cafeteria = revenues.get(brand);
            return revenues_of_cafeteria.getMonthTotal(year, month);
        } catch (InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }

    @Override
    public void addToDay(String brand, int year, int month, int day, double amount) throws InvalidDateException {
        try {
            Revenues revenues_of_cafeteria = revenues.get(brand);
            revenues_of_cafeteria.addToDay(year, month, day, amount);
        } catch (InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
}
