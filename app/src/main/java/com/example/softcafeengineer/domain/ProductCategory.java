package com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class ProductCategory
{
    private String name;
    private String description;

    // Default constructor
    public ProductCategory() {};

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setDescription(String desc) { this.description = desc; }
    public String getDescription() { return this.description; }
}
