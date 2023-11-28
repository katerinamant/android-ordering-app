package main.java.com.example.softcafeengineer.domain;

import java.util.ArrayList;

public class Product 
{
    private float price;
    private String name; 
    private boolean availability;
    private ProductCategory pc;
    //thelei/exoume logo gia array list me product info?     
    
    protected void setProductInfo(float price, String name, boolean avvailability) {};
    protected void changeAvailability() {};
    protected void changePrice(float price) {};
    public float getPrice() {return price;};
    public boolean getAvailability() {return availability;};
    public String getName() {return name;};
    public ProductCategory getCategory() {return pc;};
    public void setCategory() {};
}