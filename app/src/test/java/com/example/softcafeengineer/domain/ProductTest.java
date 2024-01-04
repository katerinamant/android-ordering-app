package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest
{
    @Test
    public void set_values() {
        Product prod = new Product();
        ProductCategory pc = new ProductCategory();

        prod.setPrice(10.0);
        Assert.assertEquals(10.0, prod.getPrice(), 0.0);

        prod.setName("Kafes");
        Assert.assertEquals("Kafes", prod.getName());

        prod.setAvailability(true);
        Assert.assertTrue(prod.getAvailability());

        prod.setCategory(pc);
        Assert.assertEquals(pc, prod.getCategory());

        Cafeteria cafe = new Cafeteria();
        prod.setCafe(cafe);
        Assert.assertEquals(cafe, prod.getCafe());
    }

    @Test
    public void constructor_with_args() {
        ProductCategory cat = new ProductCategory("coffee", "coffee",new Cafeteria());
        Cafeteria cafe = new Cafeteria();
        Product prod = new Product(10.0, "Kafes", true, cat, cafe);

        Assert.assertEquals(10.0, prod.getPrice(), 0.0);
        Assert.assertEquals("Kafes", prod.getName());
        Assert.assertTrue(prod.getAvailability());
        Assert.assertEquals(cat, prod.getCategory());
        Assert.assertEquals(cafe, prod.getCafe());
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
