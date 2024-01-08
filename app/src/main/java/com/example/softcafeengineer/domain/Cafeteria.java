package com.example.softcafeengineer.domain;

public class Cafeteria {
    private String address;
    private String phoneNumber;
    private String TIN;
    private String brand;

    // Default constructor
    public Cafeteria() {
    }

    //Constructor with arguments
    public Cafeteria(String address, String phoneNumber, String TIN, String brand) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.TIN = TIN;
        this.brand = brand;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getTIN() {
        return this.TIN;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }
}
