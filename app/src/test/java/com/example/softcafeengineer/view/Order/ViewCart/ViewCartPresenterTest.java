package com.example.softcafeengineer.view.Order.ViewCart;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.domain.Product;
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
    @Test
    public void testOnSubmitOrder(){
        presenter.onSubmitOrder();
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }
    @Test
    public void testGetActiveOrdersDAO(){
        Assert.assertEquals(presenter.getActiveOrdersDAO(), activeOrdersDAO);
    }
    @Test
    public void testDisabledConfirmButton(){
        presenter.onEditProductInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , false, true, 2, "", "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testZeroQuantity(){
        presenter.onEditProductInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , true, true, 2, "", "0", "");
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }
    @Test
    public void testInvalidInput(){
        presenter.onEditProductInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , true, true, 1, "", "-1", "");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }


}
