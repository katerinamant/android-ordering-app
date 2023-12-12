package com.example.softcafeengineer.domain;

import java.util.*;

public class Cafeteria
{
    private String address;
    private String phoneNumber;
    private String SSN;
    private String brand;

    // Default constructor
    public Cafeteria() { }

    //Constructor with arguments
    public Cafeteria(String address, String phoneNumber, String SSN, String brand) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.brand = brand;
    }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return this.address; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPhoneNumber() { return this.phoneNumber; }

    public void setSSN(String SSN) { this.SSN = SSN; }
    public String getSSN() { return this.SSN; }

    public void setBrand(String brand) { this.brand = brand; }
    public String getBrand() { return this.brand; }
}
