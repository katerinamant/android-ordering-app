package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest
{
    @Test
    public void enum_status() {
        Assert.assertEquals("IN_PROGRESS", Order.Status.IN_PROGRESS.name());
        Assert.assertEquals("CANCELED", Order.Status.CANCELED.name());
        Assert.assertEquals("WAITING", Order.Status.WAITING.name());
        Assert.assertEquals("COMPLETED", Order.Status.COMPLETED.name());
    }

    @Test
    public void default_constructor(){
        Order order = new Order();

        Assert.assertEquals(Order.Status.WAITING, order.getOrderStatus());
        Assert.assertTrue(order.getOrderList().isEmpty());
        Assert.assertEquals(0.0, order.getTotalCost(), 0.0);
    }

    @Test
    public void set_values() {
        Order order = new Order();

        order.setDate("1-1-1");
        Assert.assertEquals("1-1-1", order.getDate());
        Table table = new Table();
        order.setTable(table);
        Assert.assertEquals(table, order.getTable());

        order.setOrderStatus(Order.Status.CANCELED);
        Assert.assertEquals(Order.Status.CANCELED, order.getOrderStatus());

        order.setTotalCost(10.0);
        Assert.assertEquals(10.0, order.getTotalCost(), 0.0);
    }

    @Test
    public void constructor_with_args() {
        Table table = new Table();
        Order order = new Order("1-1-1", table);

        Assert.assertEquals(Order.Status.WAITING, order.getOrderStatus());
        Assert.assertTrue(order.getOrderList().isEmpty());
        Assert.assertEquals(0.0, order.getTotalCost(), 0.0);
        Assert.assertEquals("1-1-1", order.getDate());
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

        Product prod1 = new Product(10.0, "1", true);
        OrderInfo order_line1 = new OrderInfo(1, prod1, "");
        order.addToOrder(order_line1);
        order.calculateCost();
        Assert.assertEquals(10.0, order.getTotalCost(), 0.0);

        Product prod2 = new Product(20.0, "2", true);
        OrderInfo order_line2 = new OrderInfo(5, prod2, "");
        order.addToOrder(order_line2);
        order.calculateCost();
        Assert.assertEquals(110.0, order.getTotalCost(), 0.0);
    }
}
