package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest
{
    @Test
    public void enum_status() {
        Assert.assertEquals("IN_PROGRESS", Status.IN_PROGRESS.name());
        Assert.assertEquals("CANCELED", Status.CANCELED.name());
        Assert.assertEquals("WAITING", Status.WAITING.name());
        Assert.assertEquals("COMPLETED", Status.COMPLETED.name());
    }

    @Test
    public void default_constructor(){
        Order order = new Order();

        Assert.assertEquals(Status.WAITING, order.getOrderStatus());
        Assert.assertTrue(order.getOrderList().isEmpty());
        Assert.assertEquals(0.0, order.getTotalCost(), 0.0);
    }

    @Test
    public void set_values() throws Exception {
        Order order = new Order();

        Date date = new Date(1, 1, 1);
        order.setDate(date);
        Assert.assertEquals(date, order.getDate());
        Table table = new Table();
        order.setTable(table);
        Assert.assertEquals(table, order.getTable());
        Barista barista = new Barista();
        order.setBarista(barista);
        Assert.assertEquals(barista, order.getBarista());

        order.setOrderStatus(Status.CANCELED);
        Assert.assertEquals(Status.CANCELED, order.getOrderStatus());

        order.setTotalCost(10.0);
        Assert.assertEquals(10.0, order.getTotalCost(), 0.0);
    }

    @Test
    public void constructor_with_args() throws Exception {
        Table table = new Table();
        Date date = new Date(1, 1, 1);
        Order order = new Order(date, table);

        Assert.assertEquals(Status.WAITING, order.getOrderStatus());
        Assert.assertTrue(order.getOrderList().isEmpty());
        Assert.assertEquals(0.0, order.getTotalCost(), 0.0);
        Assert.assertEquals(date, order.getDate());
        Assert.assertEquals(table, order.getTable());
    }

    @Test
    public void order_list() {
        Order order = new Order();

        Assert.assertTrue(order.getOrderList().isEmpty());
        OrderInfo new_order_line = new OrderInfo();
        order.addToOrder(new_order_line);
        Assert.assertTrue(order.getOrderList().contains(new_order_line));
        Assert.assertEquals(1, order.getOrderList().size());
        order.removeFromOrder(new_order_line);
        Assert.assertFalse(order.getOrderList().contains(new_order_line));
        Assert.assertTrue(order.getOrderList().isEmpty());
    }

    @Test
    public void calculate_cost() {
        Order order = new Order();
        ProductCategory pc = new ProductCategory();
        Cafeteria cafe = new Cafeteria();

        Product prod1 = new Product(10.0, "1", true, pc, cafe);
        OrderInfo order_line1 = new OrderInfo(1, prod1, "");
        order.addToOrder(order_line1);
        order.calculateCost();
        Assert.assertEquals(10.0, order.getTotalCost(), 0.0);

        Product prod2 = new Product(20.0, "2", true, pc, cafe);
        OrderInfo order_line2 = new OrderInfo(5, prod2, "");
        order.addToOrder(order_line2);
        order.calculateCost();
        Assert.assertEquals(110.0, order.getTotalCost(), 0.0);
    }

    @Test
    public void execute_order() throws Exception {
        Order order = new Order();

        Barista barista = new Barista();
        order.executeOrder(barista);
        Assert.assertEquals(Status.IN_PROGRESS, order.getOrderStatus());
        Assert.assertEquals(barista, order.getBarista());
    }

    @Test(expected = InvalidStatusException.class)
    public void execute_order_exception() throws Exception {
        Order order = new Order();

        order.setOrderStatus(Status.COMPLETED);
        order.executeOrder(new Barista());
    }

    @Test
    public void complete_order() throws Exception {
        Order order = new Order();

        Barista barista = new Barista();
        order.executeOrder(barista);
        order.completeOrder();
        Assert.assertEquals(Status.COMPLETED, order.getOrderStatus());
    }

    @Test(expected = InvalidStatusException.class)
    public void complete_order_exception() throws Exception {
        Order order = new Order();

        order.setOrderStatus(Status.COMPLETED);
        order.completeOrder();
    }

    @Test
    public void cancel_order() throws Exception {
        Order order = new Order();

        order.cancelOrder();
        Assert.assertEquals(Status.CANCELED, order.getOrderStatus());
    }

    @Test(expected = InvalidStatusException.class)
    public void cancel_order_exception() throws Exception {
        Order order = new Order();

        order.setOrderStatus(Status.COMPLETED);
        order.cancelOrder();
    }
}
