package com.example.softcafeengineer.domain;

public class OrderInfo
{
    private int quantity;
    private Product product;
    private String description;

    // Default constructor
    public OrderInfo() { }

    public OrderInfo(int quantity, Product product, String description) {
        this.quantity = quantity;
        this.product = product;
        this.description = description;
    }

    public void setQuantity(int quantity) throws InvalidInputException {
        if (quantity < 0) {
            throw new InvalidInputException("Invalid quantity input!");
        } else {
            this.quantity = quantity;
        }
    }
    public int getQuantity() { return this.quantity; }

    public void setProduct(Product product) { this.product = product; }
    public Product getProduct() { return this.product; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return this.description; }

    public double calculateCost() { return this.product.getPrice() * quantity; }
}
