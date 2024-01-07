package com.example.softcafeengineer.revenue;

import com.example.softcafeengineer.domain.InvalidDateException;

import java.util.HashMap;

public class YearlyRevenues
{
    private int year;
    private HashMap<Integer, MonthlyRevenues> revenues_by_month; // month : MonthlyRevenues obj
    private String brand;

    public YearlyRevenues(String brand, int year) {
        this.revenues_by_month = new HashMap<Integer, MonthlyRevenues>();
        this.brand = brand;
        this.year = year;
    }

    public void setCafeBrand(String brand) { this.brand = brand; }
    public String getCafeBrand() { return this.brand; }

    public void setYear(int year) { this.year = year; }
    public int getYear() { return year; }

    public void addMonth(int month, MonthlyRevenues monthlyRevenues) {
        this.revenues_by_month.put(month, monthlyRevenues);
    }

    public double getDay(int month, int day) throws InvalidDateException {
        // Handle invalid date input
        if(month <= 0 || month >= 13) {
            throw new InvalidDateException("Invalid month input");
        }

        if(!revenues_by_month.containsKey(month)) {
            return -1.0;
        }
        MonthlyRevenues revenues_of_month = this.revenues_by_month.get(month);
        return revenues_of_month.getDay(day);
    }

    public double getMonthTotal(int month) throws InvalidDateException {
        // Handle invalid date input
        if(month <= 0 || month >= 13) {
            throw new InvalidDateException("Invalid month input");
        }

        if(!revenues_by_month.containsKey(month)) {
            return -1.0;
        }
        MonthlyRevenues revenues_of_month = this.revenues_by_month.get(month);
        return revenues_of_month.getMonthTotal();
    }

    public void addToDay(int month, int day, double amount) throws InvalidDateException {
        // Handle invalid date input
        if(month <= 0 || month >= 13) {
            throw new InvalidDateException("Invalid month input");
        }

        if(!revenues_by_month.containsKey(month)) {
            MonthlyRevenues new_month = new MonthlyRevenues(this.brand, month);
            revenues_by_month.put(month, new_month);
        }
        MonthlyRevenues revenues_of_month = this.revenues_by_month.get(month);
        revenues_of_month.addToDay(day, amount);
    }
}
