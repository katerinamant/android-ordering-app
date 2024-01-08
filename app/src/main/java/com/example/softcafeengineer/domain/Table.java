package com.example.softcafeengineer.domain;


public class Table {
    private String QRCode; // the unique id for customers to use to submit orders
    private int id; // the id within the cafe for the employees to recognise the table
    private Cafeteria cafe;

    // Default constructor
    public Table() {
    }

    public Table(String QRCode, int id, Cafeteria cafe) {
        this.QRCode = QRCode;
        this.id = id;
        this.cafe = cafe;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCafe(Cafeteria cafe) {
        this.cafe = cafe;
    }

    public Cafeteria getCafe() {
        return this.cafe;
    }
}
