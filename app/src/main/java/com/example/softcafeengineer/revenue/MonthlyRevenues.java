package com.example.softcafeengineer.revenue;

import com.example.softcafeengineer.domain.Cafeteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MonthlyRevenues
{
    private HashMap<String, ArrayList<Double>> revenue;  // "month-year" : [dailyRevenues]
    private HashMap<String, Double> monthly_total; // "month-year" : total
    private String brand;

    public MonthlyRevenues(String brand) {
        this.revenue = new HashMap<String, ArrayList<Double>>();
        this.monthly_total = new HashMap<String, Double>();
        this.brand = brand;
    }

    public boolean containsMonth(String key) {
        return this.revenue.containsKey(key);
    }

    public void setDay(String key, int day, double amount) {
        if(!this.revenue.containsKey(key)) {
            // Today's month and year is not in the HashMap
            ArrayList<Double> month = new ArrayList<Double>(Collections.nCopies(31, -1.0));
            month.add(day - 1, amount);
            this.revenue.put(key, month);
            this.monthly_total.put(key, amount);
        } else {
            this.revenue.get(key).add(day - 1, amount);
            double previous_total = this.monthly_total.get(key);
            this.monthly_total.put(key, previous_total + amount);
        }
    }

    public double getDay(String key, int day) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        return this.revenue.get(key).get(day - 1);
    }

    public double getMonthTotal(String key) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        return this.monthly_total.get(key);
    }

    public void setCafeBrand(String brand) { this.brand = brand; }
    public String getCafeBrand() { return this.brand; }
}
