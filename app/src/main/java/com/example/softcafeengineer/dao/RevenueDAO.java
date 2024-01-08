package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.InvalidDateException;

public interface RevenueDAO {
    /**
     * Add new Cafeteria to HashMap
     */
    void addCafeteria(String brand);

    /**
     * Check if the hashmap
     * contains a cafeteria
     */
    boolean containsCafeteria(String brand);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String old_brand, String new_brand);

    /**
     * Get total of a specific day
     * for a specific cafeteria
     */
    double getDay(String brand, int year, int month, int day) throws InvalidDateException;

    /**
     * Get total of a specific month
     * for a specific cafeteria
     */
    double getMonthTotal(String brand, int year, int month) throws InvalidDateException;

    /**
     * Add to day's total
     * for a specific cafeteria
     */
    void addToDay(String brand, int year, int month, int day, double amount) throws InvalidDateException;
}
