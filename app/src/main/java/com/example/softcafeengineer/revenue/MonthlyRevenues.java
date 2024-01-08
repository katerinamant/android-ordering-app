package com.example.softcafeengineer.revenue;

import com.example.softcafeengineer.domain.InvalidDateException;

import java.util.HashMap;

public class MonthlyRevenues {
    private int month;
    private final HashMap<Integer, Double> revenues; // day : daily total
    private String brand;

    public MonthlyRevenues(String brand, int month) {
        this.revenues = new HashMap<Integer, Double>();
        this.brand = brand;
        this.month = month;
    }

    public void setCafeBrand(String brand) {
        this.brand = brand;
    }

    public String getCafeBrand() {
        return this.brand;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return this.month;
    }

    public double getDay(int day) throws InvalidDateException {
        // Handle invalid date input
        if (day <= 0 || day >= 32) {
            throw new InvalidDateException("Invalid day input");
        }

        if (!this.revenues.containsKey(day)) {
            return -1.0;
        }
        return this.revenues.get(day);
    }

    public double getMonthTotal() {
        double total = 0.0;
        for (double amount : this.revenues.values()) {
            total += amount;
        }
        return total;
    }

    public void addToDay(int day, double amount) throws InvalidDateException {
        // Handle invalid date input
        if (day <= 0 || day >= 32) {
            throw new InvalidDateException("Invalid day input");
        }

        if (this.revenues.containsKey(day)) {
            double prev_amount = this.revenues.get(day);
            double new_amount = prev_amount + amount;
            this.revenues.put(day, new_amount);
        } else {
            this.revenues.put(day, amount);
        }
    }
}
