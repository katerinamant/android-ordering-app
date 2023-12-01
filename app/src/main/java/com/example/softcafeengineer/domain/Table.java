package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Table {
    private int QRCode;
    private int id;

    // Default constructor
    public Table() { }

    public Table(int QRCode, int id) {
        this.QRCode = QRCode;
        this.id = id;
    }

    public void setQRCode(int QRCode) { this.QRCode = QRCode; }
    public int getQRCode() { return QRCode; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}
