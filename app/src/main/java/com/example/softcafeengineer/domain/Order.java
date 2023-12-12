package com.example.softcafeengineer.domain;

import java.util.*;

public class Order
{
    private Status status;
    private ArrayList<OrderInfo> orderList;
    private Date date;
    private double totalCost;
    private Table registered_by;
    private Barista barista;

    // Default constructor
    public Order() {
        this.status = Status.WAITING;
        this.orderList = new ArrayList<OrderInfo>();
        this.totalCost = 0;
    }

    public Order(Date date, Table table) {
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

    public void setDate(Date date) { this.date = date; }
    public Date getDate() { return this.date; }

    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public double getTotalCost() { return this.totalCost; }

    public void calculateCost() {
        double res = 0;
        for(OrderInfo orderLine : orderList)
        {
            res += orderLine.calculateCost();
        }
        this.totalCost = res;
    }

    public void setTable(Table table) { this.registered_by = table; }
    public Table getTable() { return this.registered_by; }

    public void order_execution() { status = Status.IN_PROGRESS;}
    public void cancelOrder() { status = Status.CANCELED; }
    public void orderCompleted() { status = Status.COMPLETED;}

    public void setBarista(Barista bar) { this.barista = bar; }
    public Barista getBarista() { return this.barista; }


}
