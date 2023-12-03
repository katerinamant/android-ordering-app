package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class ProductCategoryTest
{
    @Test
    public void set_values() {
        ProductCategory category = new ProductCategory();

        Assert.assertTrue(category.getProductsList().isEmpty());

        category.setName("Kafedes");
        Assert.assertEquals("Kafedes", category.getName());

        category.setDescription("Zestoi kai kryoi kafedes");
        Assert.assertEquals("Zestoi kai kryoi kafedes", category.getDescription());
    }

    @Test
    public void constructor_with_args() {
        ProductCategory category = new ProductCategory("Kafedes", "Zestoi kai kryoi kafedes");

        Assert.assertEquals("Kafedes", category.getName());
        Assert.assertEquals("Zestoi kai kryoi kafedes", category.getDescription());
        Assert.assertTrue(category.getProductsList().isEmpty());
    }

    @Test
    public void products_list() {
        ProductCategory category = new ProductCategory();

        Assert.assertTrue(category.getProductsList().isEmpty());
        Product new_product = new Product();
        category.addToProducts(new_product);
        Assert.assertTrue(category.getProductsList().contains(new_product));
        Assert.assertEquals(1, category.getProductsList().size());
        category.removeFromProducts(new_product);
        Assert.assertFalse(category.getProductsList().contains(new_product));
        Assert.assertTrue(category.getProductsList().isEmpty());
    }
}