package com.example.softcafeengineer.domain;

import java.util.*;

public class Cafeteria
{
    private String address;
    private String phoneNumber;
    private String SSN;
    private String brand;
    private ArrayList<Table> tablesList;
    private ArrayList<Product> productsList;
    private ArrayList<Barista> baristasList;

    // Default constructor
    public Cafeteria() {
        this.tablesList = new ArrayList<Table>();
        this.productsList = new ArrayList<Product>();
        this.baristasList = new ArrayList<Barista>();
    }
    //Constructor with arguments
    public Cafeteria(String address, String phoneNumber, String SSN, String brand) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.brand = brand;
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
