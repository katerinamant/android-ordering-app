package com.example.softcafeengineer.domain;

public class Product
{
    private double price;
    private String name;
    private boolean availability;
    private ProductCategory category;

    // Default constructor
    public Product() { }

    public Product(double price, String name, boolean availability, ProductCategory category) {
        this.price = price;
        this.name = name;
        this.availability = availability;
        this.category = category;
    }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return this.price; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setAvailability(boolean availability) { this.availability = availability; }
    public void toggleAvailability() { this.availability = !this.availability; }
    public boolean getAvailability() { return this.availability; }
}
