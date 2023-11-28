package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Barista extends User {
    private ArrayList<Order> orderQueue;
    private Order order; // filler for getnextorder. delete 
    public Order getNextOrder() {return order;}; //filler return gia na mhn bgazei lathos
    private void showOrderDetails(Order order) {};
    private void cancelOrder(Order order) {};
    private void cancelItem(String product) {};

}
