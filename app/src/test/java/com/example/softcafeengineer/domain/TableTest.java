package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class TableTest {
    @Test
    public void set_values() {
        Table table = new Table();

        table.setQRCode("1");
        Assert.assertEquals("1", table.getQRCode());

        table.setId(1);
        Assert.assertEquals(1, table.getId());

        Cafeteria cafe = new Cafeteria();
        table.setCafe(cafe);
        Assert.assertEquals(cafe, table.getCafe());
    }

    @Test
    public void constructor_with_args() {
        Cafeteria cafe = new Cafeteria();
        Table table = new Table("1", 1, cafe);

        Assert.assertEquals("1", table.getQRCode());
        Assert.assertEquals(1, table.getId());
        Assert.assertEquals(cafe, table.getCafe());
    }
}
