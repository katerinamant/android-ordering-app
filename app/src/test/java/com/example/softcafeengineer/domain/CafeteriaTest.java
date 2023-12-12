package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class CafeteriaTest
{
    @Test
    public void default_constructor(){
        Cafeteria cafe = new Cafeteria();

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
        Assert.assertTrue(cafe.getTablesList().isEmpty());
        Assert.assertTrue(cafe.getProductsList().isEmpty());
        Assert.assertTrue(cafe.getBaristasList().isEmpty());
    }


    @Test
    public void tables_list() {
        Cafeteria cafe = new Cafeteria();

        Assert.assertTrue(cafe.getTablesList().isEmpty());
        Table new_table = new Table();
        cafe.addToTables(new_table);
        Assert.assertTrue(cafe.getTablesList().contains(new_table));
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
        Assert.assertTrue(cafe.getProductsList().contains(new_product));
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
        Assert.assertTrue(cafe.getBaristasList().contains(new_barista));
        Assert.assertEquals(1, cafe.getBaristasList().size());
        cafe.removeFromBaristas(new_barista);
        Assert.assertFalse(cafe.getBaristasList().contains(new_barista));
        Assert.assertTrue(cafe.getBaristasList().isEmpty());
    }
}