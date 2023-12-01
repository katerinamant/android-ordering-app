package com.example.softcafeengineer.domain;

public class Barista extends User
{
    private Order executingOrder;

    // Default constructor
    public Barista() { super(); }

    public Barista(String user, String pass) {
        super(user, pass);
    }

    public Order getExecutingOrder() { return this.executingOrder; }
}
