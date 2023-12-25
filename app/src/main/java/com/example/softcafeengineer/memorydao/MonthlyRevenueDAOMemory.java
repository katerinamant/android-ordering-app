package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.MonthlyRevenueDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MonthlyRevenueDAOMemory implements MonthlyRevenueDAO
{
    private HashMap<String, ArrayList<Double>> revenue = new HashMap<String, ArrayList<Double>>(); // "month-year" : [dailyRevenues]

    @Override
    public boolean containsMonth(String key) {
        return this.revenue.containsKey(key);
    }

    @Override
    public double getDay(String key, int day) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        return this.revenue.get(key).get(day - 1);
    }

    @Override
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

    @Override
    public double getMonthTotal(String key) {
        // No need to check if it contains this key
        // As long as we use containsMonth first.
        double total = 0;
        for(int i=0; i<31; i++) {
            total += this.revenue.get(key).get(i);
        }
        return total;
    }
}
