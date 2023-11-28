package main.java.com.example.softcafeengineer.domain;

import java.util.*;

public class Order {
    private float totalCost;
    private Date date;
    private String status; 
    private Table table;

    protected void setOrderInfo(float totalCost, Date date, String status) {};
    public void setOrderStatus(String status) {};
    public String getOrderStatus() {return status;};
    public void addRevenue(float totalCost) {};
    public Date getDate() {return date;};
    public float getTotalCost() {return totalCost;};
    public void removeItem(Product product) {};
    public void setTable(Table table) {};
    public Table getTable() {return table;};

    //enumeration??
}
