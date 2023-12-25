package com.example.softcafeengineer.dao;

public interface MonthlyRevenueDAO
{
    /**
     * Check if there are records
     * for a specific month
     */
    boolean containsMonth(String key);

    /**
     * Retrieve data for specific day
     */
    double getDay(String key, int day);

    /**
     * Set day's revenue
     */
    void setDay(String key, int day, double amount);

    /**
     * Retrieve monthly total for specific month
     */
    double getMonthTotal(String key);
}
