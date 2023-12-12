package com.example.softcafeengineer.domain;

public class Barista extends User
{
    private Cafeteria cafe;

    // Default constructor
    public Barista() { super(); }

    public Barista(String user, String pass, Cafeteria cafe) {
        super(user, pass);
        this.cafe = cafe;
    }

    public void setCafe(Cafeteria cafe) { this.cafe = cafe; }
    public Cafeteria getCafe() { return this.cafe; }
}
