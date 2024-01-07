package com.example.softcafeengineer.view.Order.ViewCart;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class ViewCartPresenterTest {
    private ViewCartViewStub view;
    private ViewCartPresenter presenter;
    private ActiveOrdersDAO activeOrdersDAO;
    private Order order;
    private Cafeteria cafe;
    private Table table;
    @Before
    public void setUp() throws InvalidDateException {
        view = new ViewCartViewStub();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        cafe = new Cafeteria("address", "0123456789", "123456789", "cafe_brand");
        table = new Table("QRCode", 15, cafe);
        order = new Order(new Date(1,1,2024), table);
        order.setTotalCost(10.20);

        activeOrdersDAO.save(order);
        presenter = new ViewCartPresenter();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setView(view, order.getTable().getQRCode());
    }
    @Test
    public void testGetTotalCost(){
        Assert.assertEquals(presenter.getTotalCost(), order.getTotalCost(), 0.0);
    }
    @Test
    public void testGetOrderResults(){
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }
}
