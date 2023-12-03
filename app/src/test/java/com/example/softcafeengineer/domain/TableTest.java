package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class TableTest
{
    @Test
    public void set_values() {
        Table table = new Table();

        table.setQRCode(1);
        Assert.assertEquals(1, table.getQRCode());

        table.setId(1);
        Assert.assertEquals(1, table.getId());
    }

    @Test
    public void constructor_with_args() {
        Table table = new Table(1, 1);

        Assert.assertEquals(1, table.getQRCode());
        Assert.assertEquals(1, table.getId());
    }
}
