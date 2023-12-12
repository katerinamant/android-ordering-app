package com.example.softcafeengineer.domain;

public class Product
{
    private double price;
    private String name;
    private boolean availability;
    private ProductCategory category;
    private Cafeteria cafe;

    // Default constructor
    public Product() { }

    public Product(double price, String name, boolean availability, ProductCategory category, Cafeteria cafe) {
        this.price = price;
        this.name = name;
        this.availability = availability;
        this.category = category;
        this.cafe = cafe;
    }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return this.price; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setAvailability(boolean availability) { this.availability = availability; }
    public void toggleAvailability() { this.availability = !this.availability; }
    public boolean getAvailability() { return this.availability; }

    public void setCategory(ProductCategory category) {this.category = category;}
    public ProductCategory getCategory() {return this.category;}

    public void setCafe(Cafeteria cafe) { this.cafe = cafe; }
    public Cafeteria getCafe() { return this.cafe; }
}
