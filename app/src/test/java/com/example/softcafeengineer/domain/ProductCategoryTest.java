package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductCategoryTest {
    @Test
    public void set_values() {
        ProductCategory category = new ProductCategory();

        category.setName("Kafedes");
        Assert.assertEquals("Kafedes", category.getName());

        category.setDescription("Zestoi kai kryoi kafedes");
        Assert.assertEquals("Zestoi kai kryoi kafedes", category.getDescription());

        Cafeteria cafe = new Cafeteria();
        category.setCafe(cafe);
        Assert.assertSame(cafe, category.getCafe());
    }

    @Test
    public void constructor_with_args() {
        ProductCategory category = new ProductCategory("Kafedes", "Zestoi kai kryoi kafedes", new Cafeteria());

        Assert.assertEquals("Kafedes", category.getName());
        Assert.assertEquals("Zestoi kai kryoi kafedes", category.getDescription());
    }
}
