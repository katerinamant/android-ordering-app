package com.example.softcafeengineer.revenue;

import com.example.softcafeengineer.domain.InvalidDateException;

import java.util.HashMap;

public class Revenues
{
    private String brand;
    private HashMap<Integer, YearlyRevenues> revenues_by_year; // year : YearlyRevenues obj

    public Revenues(String brand) {
        this.revenues_by_year = new HashMap<Integer, YearlyRevenues>();
        this.brand = brand;
    }

    public void setCafeBrand(String brand) { this.brand = brand; }
    public String getCafeBrand() { return this.brand; }

    public double getDay(int year, int month, int day) throws InvalidDateException {
        if(!revenues_by_year.containsKey(year)) {
            return -1.0;
        }

        try {
            YearlyRevenues revenues_of_year = this.revenues_by_year.get(year);
            return revenues_of_year.getDay(month, day);
        } catch (InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }

    public double getMonthTotal(int year, int month) throws InvalidDateException {
        if(!revenues_by_year.containsKey(year)) {
            return -1.0;
        }

        YearlyRevenues revenues_of_year = this.revenues_by_year.get(year);
        return revenues_of_year.getMonthTotal(month);
    }

    public void addToDay(int year, int month, int day, double amount) throws InvalidDateException {
        if(!revenues_by_year.containsKey(year)) {
            YearlyRevenues new_year = new YearlyRevenues(this.brand, year);
            revenues_by_year.put(year, new_year);
        }

        YearlyRevenues revenues_of_year = this.revenues_by_year.get(year);
        revenues_of_year.addToDay(month, day, amount);
    }
}
