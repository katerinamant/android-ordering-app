package com.example.softcafeengineer.dao;

public interface MonthlyRevenueDAO
{
    /**
     * Check if one Cafeteria has records
     */
    boolean containsCafeteria(String brand);

    /**
     * Add new Cafeteria to HashMap
     */
    void addCafeteria(String brand);

    /**
     * Check if there are records
     * for a specific month
     * for a specific Cafeteria
     */
    boolean containsMonth(String brand, String key);

    /**
     * Retrieve data for specific day
     * for a specific Cafeteria
     */
    double getDay(String brand, String key, int day);

    /**
     * Set day's revenue
     * for a specific cafeteria
     */
    void setDay(String brand, String key, int day, double amount);

    /**
     * Retrieve monthly total for specific month
     * for a specific cafeteria
     */
    double getMonthTotal(String brand, String key);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String old_brand, String new_brand);

    /**
     * Increment cafeteria's
     * total of the day
     */
    void addToDay(String brand, double amount);

    /**
     * Register cafeteria's
     * total of the day
     */
    void closeDay(String brand, String key, int day);
}
