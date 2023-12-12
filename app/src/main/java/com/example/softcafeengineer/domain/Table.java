package com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Table {
    private int QRCode;
    private int id;
    private Cafeteria cafe;

    // Default constructor
    public Table() { }

    public Table(int QRCode, int id, Cafeteria cafe) {
        this.QRCode = QRCode;
        this.id = id;
        this.cafe = cafe;
    }

    public void setQRCode(int QRCode) { this.QRCode = QRCode; }
    public int getQRCode() { return QRCode; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setCafe(Cafeteria cafe) { this.cafe = cafe; }
    public Cafeteria getCafe() { return this.cafe; }
}
