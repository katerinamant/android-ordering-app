package com.example.softcafeengineer.domain;

public class ProductCategory {
    private String name;
    private String description;
    private Cafeteria cafe;

    // Default constructor
    public ProductCategory() {
    }

    public ProductCategory(String name, String description, Cafeteria cafe) {
        this.name = name;
        this.description = description;
        this.cafe = cafe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCafe(Cafeteria cafe) {
        this.cafe = cafe;
    }

    public Cafeteria getCafe() {
        return this.cafe;
    }
}
