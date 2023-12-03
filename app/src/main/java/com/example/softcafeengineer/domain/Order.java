package com.example.softcafeengineer.domain;

import java.util.*;

public class Order
{
    public enum Status {
        IN_PROGRESS,
        CANCELED,
        WAITING,
        COMPLETED
    }

    private Status status;
    private ArrayList<OrderInfo> orderList;
    private String date;
    private double totalCost;
    private Table registered_by;

    // Default constructor
    public Order() {
        this.status = Status.WAITING;
        this.orderList = new ArrayList<OrderInfo>();
        this.totalCost = 0;
    }

    public Order(String date, Table table) {
        this.status = Status.WAITING;
        this.orderList = new ArrayList<OrderInfo>();
        this.date = date;
        this.totalCost = 0;
        this.registered_by = table;
    }

    public void setOrderStatus(Status status) { this.status = status; }
    public Status getOrderStatus() { return this.status; }

    public ArrayList<OrderInfo> getOrderList() { return this.orderList; }
    public void addToOrder(OrderInfo orderLine) { this.orderList.add(orderLine); }
    public void removeFromOrder(OrderInfo orderLine) { this.orderList.remove(orderLine); }

    public void setDate(String date) { this.date = date; }
    public String getDate() { return this.date; }

    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public double getTotalCost() { return this.totalCost; }

    public void calculateCost() {
        double res = 0;
        for(OrderInfo orderLine : orderList)
        {
            Product product = orderLine.getProduct();
            int quantity = orderLine.getQuantity();
            res += quantity * product.getPrice();
        }
        this.totalCost = res;
    }

    public void setTable(Table table) { this.registered_by = table; }
    public Table getTable() { return this.registered_by; }
}
