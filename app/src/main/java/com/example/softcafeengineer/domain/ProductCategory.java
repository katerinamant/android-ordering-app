package com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class ProductCategory
{
    private String name;
    private String description;
    private ArrayList<Product> products;

    // Default constructor
    public ProductCategory() { }

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setDescription(String desc) { this.description = desc; }
    public String getDescription() { return this.description; }

    public ArrayList<Product> getProductsList() { return this.products; }
    public void addToProducts(Product product) { this.products.add(product); }
    public void removeFromProducts(Product product) { this.products.remove(product); }
}
