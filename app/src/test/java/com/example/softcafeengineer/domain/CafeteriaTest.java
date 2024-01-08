package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class CafeteriaTest {
    @Test
    public void set_values() {
        Cafeteria cafe = new Cafeteria();

        cafe.setAddress("28is Oktovriou 76");
        Assert.assertEquals("28is Oktovriou 76", cafe.getAddress());

        cafe.setPhoneNumber("2108203314");
        Assert.assertEquals("2108203314", cafe.getPhoneNumber());

        cafe.setTIN("123456789");
        Assert.assertEquals("123456789", cafe.getTIN());

        cafe.setBrand("OPA");
        Assert.assertEquals("OPA", cafe.getBrand());
    }

    @Test
    public void constructor_with_args() {
        Cafeteria cafe = new Cafeteria("28is Oktovriou 76", "2108203314", "123456789", "OPA");

        Assert.assertEquals("28is Oktovriou 76", cafe.getAddress());
        Assert.assertEquals("2108203314", cafe.getPhoneNumber());
        Assert.assertEquals("123456789", cafe.getTIN());
        Assert.assertEquals("OPA", cafe.getBrand());
    }
}
