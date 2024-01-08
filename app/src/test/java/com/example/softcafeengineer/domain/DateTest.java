package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class DateTest {
    @Test
    public void set_values() throws InvalidDateException {
        Date date = new Date();

        date.setDay(1);
        Assert.assertEquals(1, date.getDay());

        date.setMonth(1);
        Assert.assertEquals(1, date.getMonth());

        date.setYear(1);
        Assert.assertEquals(1, date.getYear());
    }

    @Test
    public void constructor_with_args() throws Exception {
        Date date = new Date(1, 1, 1);

        Assert.assertEquals(1, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(1, date.getYear());
    }

    @Test(expected = InvalidDateException.class)
    public void day_exception() throws Exception {
        Date date = new Date(0, 1, 1);
    }

    @Test(expected = InvalidDateException.class)
    public void day_exception_2() throws Exception {
        Date date = new Date();
        date.setDay(32);
    }

    @Test(expected = InvalidDateException.class)
    public void month_exception() throws Exception {
        Date date = new Date(1, 0, 1);
    }

    @Test(expected = InvalidDateException.class)
    public void month_exception_2() throws Exception {
        Date date = new Date();
        date.setMonth(13);
    }

    @Test
    public void equals() throws Exception {
        Date date1 = new Date(1, 1, 1);
        Date date2 = new Date(2, 2, 2);
        Date date3 = new Date(1, 1, 1);
        Object obj = new Object();

        Assert.assertSame(date1, date1);
        Assert.assertNotNull(date1);
        Assert.assertNotSame(date1, obj);

        Assert.assertNotSame(date1, date2);
        Assert.assertEquals(date1, date3);
    }

    @Test
    public void to_string() throws Exception {
        Date date = new Date(1, 1, 1);
        Assert.assertEquals("1 - 1 - 1", date.toString());
    }
}
