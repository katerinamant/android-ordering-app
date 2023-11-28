package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class ProductCategory {
    private String name;
    private String description;
    private ArrayList<Product> products;

    protected void setInfo(String name, String description) {}
    public String getName() {return name;};
    public String getDescription() {return description;};
    public void addProduct( Product product) {};
    public void removeProduct(Product product) {};
}
