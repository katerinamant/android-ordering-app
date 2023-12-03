package com.example.softcafeengineer.domain;

import java.util.*;

public class Cafeteria
{
    private String address;
    private String phoneNumber;
    private String SSN;
    private String brand;
    private HashMap<String, List<Double>> monthlyRevenue; // "month-year" : [dailyRevenues]
    private double todaysRevenue;
    private ArrayList<Table> tablesList;
    private ArrayList<Product> productsList;
    private ArrayList<Barista> baristasList;

    // Default constructor
    public Cafeteria() {
        this.todaysRevenue = 0;
        this.monthlyRevenue = new HashMap<String, List<Double>>();
        this.tablesList = new ArrayList<Table>();
        this.productsList = new ArrayList<Product>();
        this.baristasList = new ArrayList<Barista>();
    }

    public Cafeteria(String address, String phoneNumber, String SSN, String brand) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.brand = brand;
        this.todaysRevenue = 0;
        this.monthlyRevenue = new HashMap<String, List<Double>>();
        this.tablesList = new ArrayList<Table>();
        this.productsList = new ArrayList<Product>();
        this.baristasList = new ArrayList<Barista>();
    }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return this.address; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return this.phoneNumber; }

    public void setSSN(String SSN) { this.SSN = SSN; }
    public String getSSN() { return this.SSN; }

    public void setBrand(String brand) { this.brand = brand; }
    public String getBrand() { return this.brand; }

    public double getDailyRevenue(int day, int month, int year) throws InvalidRevenueInputException {
        String date = String.format("%d-%d", month, year);

        if (!this.monthlyRevenue.containsKey(date))
        {
            // If today's month and year is not in the HashMap
            throw new InvalidRevenueInputException(String.format("No records of month %d/%d", month, year));
        }
        else
        {
            return this.monthlyRevenue.get(date).get(day - 1);
        }
    }

    public List<Double> getMonthlyRevenue(int month, int year) throws InvalidRevenueInputException {
        String date = String.format("%d-%d", month, year);

        if (!this.monthlyRevenue.containsKey(date))
        {
            // If today's month and year is not in the HashMap
            throw new InvalidRevenueInputException(String.format("No records of month %d/%d", month, year));
        }
        else
        {
            return this.monthlyRevenue.get(date);
        }
    }

    public HashMap<String, List<Double>> getMonthlyRevenueMap() { return this.monthlyRevenue; }

    public double getTodaysRevenue() { return this.todaysRevenue; }

    public void increaseRevenue(double amount) {
        this.todaysRevenue += amount;
    }

    public void closeDay(int day, int month, int year) {
        String date = String.format("%d-%d", month, year);

        if (!this.monthlyRevenue.containsKey(date))
        {
            // If today's month and year is not in the HashMap
            List<Double> new_month = new ArrayList<Double>(Collections.nCopies(31, null));
            new_month.add(day - 1, this.todaysRevenue);
            this.monthlyRevenue.put(date, new_month);
        }
        else
        {
            // Today's month and year is in the HashMap
            this.monthlyRevenue.get(date).add(day - 1, this.todaysRevenue);
        }
        this.todaysRevenue = 0;
    }

    public ArrayList<Table> getTablesList() { return this.tablesList; }
    public void addToTables(Table table) { this.tablesList.add(table); }
    public void removeFromTables(Table table) { this.tablesList.remove(table); }

    public ArrayList<Product> getProductsList() { return this.productsList; }
    public void addToProducts(Product product) { this.productsList.add(product); }
    public void removeFromProducts(Product product) { this.productsList.remove(product); }

    public ArrayList<Barista> getBaristasList() { return this.baristasList; }
    public void addToBaristas(Barista barista) { this.baristasList.add(barista); }
    public void removeFromBaristas(Barista barista) { this.baristasList.remove(barista); }
}
