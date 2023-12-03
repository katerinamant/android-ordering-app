package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class CafeteriaTest
{
    @Test
    public void default_constructor(){
        Cafeteria cafe = new Cafeteria();

        Assert.assertEquals(0.0, cafe.getTodaysRevenue(), 0.0);
        Assert.assertTrue(cafe.getMonthlyRevenueMap().isEmpty());
        Assert.assertTrue(cafe.getTablesList().isEmpty());
        Assert.assertTrue(cafe.getProductsList().isEmpty());
        Assert.assertTrue(cafe.getBaristasList().isEmpty());
    }

    @Test
    public void set_values() {
        Cafeteria cafe = new Cafeteria();

        cafe.setAddress("28is Oktovriou 76");
        Assert.assertEquals("28is Oktovriou 76", cafe.getAddress());

        cafe.setPhoneNumber("2108203314");
        Assert.assertEquals("2108203314", cafe.getPhoneNumber());

        cafe.setSSN("123456789");
        Assert.assertEquals("123456789", cafe.getSSN());

        cafe.setBrand("OPA");
        Assert.assertEquals("OPA", cafe.getBrand());
    }

    @Test
    public void constructor_with_args() {
        Cafeteria cafe = new Cafeteria("28is Oktovriou 76", "2108203314", "123456789", "OPA");

        Assert.assertEquals("28is Oktovriou 76", cafe.getAddress());
        Assert.assertEquals("2108203314", cafe.getPhoneNumber());
        Assert.assertEquals("123456789", cafe.getSSN());
        Assert.assertEquals("OPA", cafe.getBrand());
        Assert.assertEquals(0.0, cafe.getTodaysRevenue(), 0.0);
        Assert.assertTrue(cafe.getMonthlyRevenueMap().isEmpty());
        Assert.assertTrue(cafe.getTablesList().isEmpty());
        Assert.assertTrue(cafe.getProductsList().isEmpty());
        Assert.assertTrue(cafe.getBaristasList().isEmpty());
    }

    @Test
    public void increase_revenue() {
       Cafeteria cafe = new Cafeteria();

       Assert.assertEquals(0, cafe.getTodaysRevenue(), 0.0);
       cafe.increaseRevenue(15.0);
       Assert.assertEquals(15.0, cafe.getTodaysRevenue(), 0.0);
    }

    @Test
    public void revenue() throws Exception {
        Cafeteria cafe = new Cafeteria();

        cafe.increaseRevenue(15.0);
        cafe.closeDay(3, 12, 2023); // creates December 2023 entry in HashMap
        Assert.assertEquals(15.0, cafe.getDailyRevenue(3, 12, 2023), 0.0);
        Assert.assertEquals(0.0, cafe.getTodaysRevenue(), 0.0); // after closing, todaysRevenue is again 0

        cafe.increaseRevenue(20.0);
        cafe.closeDay(4, 12, 2023); // December 2023 already in Hashmap
        Assert.assertEquals(20.0, cafe.getDailyRevenue(4, 12, 2023), 0.0);

        List<Double> december = cafe.getMonthlyRevenue(12, 2023);
        Assert.assertNull(december.get(0));
        Assert.assertEquals(15.0, december.get(3 - 1), 0.0);
        Assert.assertEquals(20.0, december.get(4 - 1), 0.0);
    }

    @Test(expected = InvalidRevenueInputException.class)
    public void daily_revenue_exception() throws Exception {
        Cafeteria cafe = new Cafeteria();
        double dec_first = cafe.getDailyRevenue(1, 12, 2023);
    }

    @Test(expected = InvalidRevenueInputException.class)
    public void monthly_revenue_exception() throws Exception {
        Cafeteria cafe = new Cafeteria();
        List<Double> december = cafe.getMonthlyRevenue(12, 2023);
    }

    @Test
    public void tables_list() {
        Cafeteria cafe = new Cafeteria();

        Assert.assertTrue(cafe.getTablesList().isEmpty());
        Table new_table = new Table();
        cafe.addToTables(new_table);
        Assert.assertEquals(1, cafe.getTablesList().size());
        cafe.removeFromTables(new_table);
        Assert.assertFalse(cafe.getTablesList().contains(new_table));
        Assert.assertTrue(cafe.getTablesList().isEmpty());
    }

    @Test
    public void products_list() {
        Cafeteria cafe = new Cafeteria();

        Assert.assertTrue(cafe.getProductsList().isEmpty());
        Product new_product = new Product();
        cafe.addToProducts(new_product);
        Assert.assertEquals(1, cafe.getProductsList().size());
        cafe.removeFromProducts(new_product);
        Assert.assertFalse(cafe.getProductsList().contains(new_product));
        Assert.assertTrue(cafe.getProductsList().isEmpty());
    }

    @Test
    public void baristas_list() {
        Cafeteria cafe = new Cafeteria();

        Assert.assertTrue(cafe.getBaristasList().isEmpty());
        Barista new_barista = new Barista();
        cafe.addToBaristas(new_barista);
        Assert.assertEquals(1, cafe.getBaristasList().size());
        cafe.removeFromBaristas(new_barista);
        Assert.assertFalse(cafe.getBaristasList().contains(new_barista));
        Assert.assertTrue(cafe.getBaristasList().isEmpty());
    }
}