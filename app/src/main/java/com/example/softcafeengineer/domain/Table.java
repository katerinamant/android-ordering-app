package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Table {
    private int QRCode;
    private int ID;
    private ArrayList<Order> orders;

    public int getQRCode() {return QRCode;};
    public int getID() {return ID;};
    public void createNewTable(int QRCode, int id) {};
    public void addOrder(Order order) {};
    public void removeOrder(Order order) {};
}
