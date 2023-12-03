package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class OrderInfoTest
{
    @Test
    public void set_values() {
        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setQuantity(2);
        Assert.assertEquals(2, orderInfo.getQuantity());

        Product product = new Product();
        orderInfo.setProduct(product);
        Assert.assertEquals(product, orderInfo.getProduct());

        orderInfo.setDescription("Xoris Zaxari");
        Assert.assertEquals("Xoris Zaxari", orderInfo.getDescription());
    }

    @Test
    public void constructor_with_args() {
        Product product = new Product();
        OrderInfo orderInfo = new OrderInfo(2, product, "Xoris Zaxari");

        Assert.assertEquals(2, orderInfo.getQuantity());
        Assert.assertEquals(product, orderInfo.getProduct());
        Assert.assertEquals("Xoris Zaxari", orderInfo.getDescription());
    }
}
