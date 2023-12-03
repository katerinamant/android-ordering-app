package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest
{
    @Test
    public void set_values() {
        Product prod = new Product();

        prod.setPrice(10.0);
        Assert.assertEquals(10.0, prod.getPrice(), 0.0);

        prod.setName("Kafes");
        Assert.assertEquals("Kafes", prod.getName());

        prod.setAvailability(true);
        Assert.assertTrue(prod.getAvailability());
    }

    @Test
    public void constructor_with_args() {
        Product prod = new Product(10.0, "Kafes", true);

        Assert.assertEquals(10.0, prod.getPrice(), 0.0);
        Assert.assertEquals("Kafes", prod.getName());
        Assert.assertTrue(prod.getAvailability());
    }

    @Test
    public void availability() {
        Product prod = new Product();

        prod.setAvailability(true);
        Assert.assertTrue(prod.getAvailability());
        prod.toggleAvailability();
        Assert.assertFalse(prod.getAvailability());
    }
}