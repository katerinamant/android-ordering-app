package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Cafeteria {
    private String address;
    private String phoneNumber;
    private String SSN;
    private String brand;
    private float DailyRevenue;
    private float[] MonthlyRevenue;
    private ArrayList<Table> tablesList = new ArrayList<Table>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Barista> baristas = new ArrayList<Barista>();
    
    protected void setInfo(String address, String phoneNumber, String SSN, String brand){}
    public float[] getMonthlyRevenue(int month, int year){return MonthlyRevenue;}
    public float getDailyRevenue(int day, int month, int year){return DailyRevenue;}
    public void calculateCost(float cost){}
    public ArrayList<Table> getTablesList(){return tablesList;}
    public void showTables(){}
    public void addToTablesList(Table table){}
    public void addToProducts(Product product){}
    public void removeFromProducts(Product product){}
    public ArrayList<Product> getProducts(){return products;}
}