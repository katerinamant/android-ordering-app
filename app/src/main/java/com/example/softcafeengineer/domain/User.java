package com.example.softcafeengineer.domain;

public class User {
    private String username;
    private String password;
    private Cafeteria cafe;

    // Default constructor
    public User() {
    }

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCafe(Cafeteria cafe) {
        this.cafe = cafe;
    }

    public Cafeteria getCafe() {
        return this.cafe;
    }
}
