package com.example.softcafeengineer.revenue;

import com.example.softcafeengineer.domain.Cafeteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MonthlyRevenues
{
    private HashMap<String, ArrayList<Double>> revenue;  // "month-year" : [dailyRevenues]
    private String brand;

    public MonthlyRevenues(String brand) {
        this.revenue = new HashMap<String, ArrayList<Double>>();
        this.brand = brand;
    }

    public boolean containsMonth(String key) {
        return this.revenue.containsKey(key);
    }

    public double getDay(String key, int day) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        return this.revenue.get(key).get(day - 1);
    }

    public void setDay(String key, int day, double amount) {
        if(!this.revenue.containsKey(key)) {
            // Today's month and year is not in the HashMap
            ArrayList<Double> month = new ArrayList<Double>(Collections.nCopies(31, -1.0));
            month.add(day - 1, amount);
            this.revenue.put(key, month);
        } else {
            this.revenue.get(key).add(day - 1, amount);
        }
    }

    public double getMonthTotal(String key) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        double total = 0;
        for(int i=0; i<31; i++) {
            total += this.revenue.get(key).get(i);
        }
        return total;
    }

    public void setCafeBrand(String brand) { this.brand = brand; }
    public String getCafeBrand() { return this.brand; }
}
