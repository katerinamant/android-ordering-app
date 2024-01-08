package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class BaristaTest {
    @Test
    public void set_values() {
        Barista barista = new Barista();

        barista.setUsername("User");
        Assert.assertEquals("User", barista.getUsername());

        barista.setPassword("1234");
        Assert.assertEquals("1234", barista.getPassword());

        Cafeteria cafe = new Cafeteria();
        barista.setCafe(cafe);
        Assert.assertEquals(cafe, barista.getCafe());
    }

    @Test
    public void constructor_with_args() {
        Cafeteria cafe = new Cafeteria();
        Barista barista = new Barista("User", "1234");

        Assert.assertEquals("User", barista.getUsername());
        Assert.assertEquals("1234", barista.getPassword());
    }
}
