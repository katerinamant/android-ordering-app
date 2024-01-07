package com.example.softcafeengineer.view.Order.ViewCart;
import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
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
    private ActiveCartsDAO activeCartsDAO;
    /**
     *  initializing all the objects needed to run tests for the
     *  ViewCartPresenter methods
     */
    @Before
    public void setUp() throws InvalidDateException {
        view = new ViewCartViewStub();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        cafe = new Cafeteria("address", "0123456789", "123456789", "cafe_brand");
        table = new Table("QRCode", 15, cafe);
        order = new Order(new Date(1,1,2024), table);
        order.setTotalCost(10.20);
        activeCartsDAO = new ActiveCartsDAOMemory();
        activeCartsDAO.save(order);

        presenter = new ViewCartPresenter();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setActiveCartsDAO(activeCartsDAO);
        presenter.setView(view, order.getTable().getQRCode());
    }
    /**
     *  testing whether the method getTotalCost returns the correct
     *  totalCost of this order
     */
    @Test
    public void testGetTotalCost(){

        Assert.assertEquals(presenter.getTotalCost(), order.getTotalCost(), 0.0);
    }
    /**
     *  testing whether the method getOrderResults get returns the correct
     *  OrderInfo for this order
     */
    @Test
    public void testGetOrderResults(){
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }

    /**
     *  testing whether the method onSubmitOrder successfully adds an order to active orders and deletes it
     *  from the active cart
     */
    @Test
    public void testOnSubmitOrder(){
        presenter.onSubmitOrder();
        Assert.assertEquals(presenter.getActiveCartsDAO().find(order.getTable().getQRCode()), activeCartsDAO.find(order.getTable().getQRCode()));
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }
    /**
     *  testing whether the method getActiveOrdersDAO returns the correct
     *  ActiveOrdersDAO object
     */
    @Test
    public void testGetActiveOrdersDAO(){
        Assert.assertEquals(presenter.getActiveOrdersDAO(), activeOrdersDAO);
    }
    /**
     *  testing whether the method getActiveCartsDAO returns the correct
     *  ActiveCartsDAO object
     */
    @Test
    public void testGetActiveCartsDAO(){
        Assert.assertEquals(presenter.getActiveCartsDAO(), activeCartsDAO);
    }
    /**
     *  testing whether the onEditOrderInfo method shows the correct toast
     *  message when the confirm button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledConfirmButton(){
        presenter.onEditOrderInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , false, true, 2, "", "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }

    /**
     *  testing whether the onEditOrderInfo method successfully removes a product from an order
     *  when its new quantity is set to zero
     */
    @Test
    public void testZeroQuantity(){
        presenter.onEditOrderInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , true, true, 2, "", "0", "");
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }
    /**
     *  testing whether the method onEditOrderInfo shows the correct error tittle and
     *  error message when the user provides a negative value for the quantity of a product
     */
    @Test
    public void testInvalidInput(){
        presenter.onEditOrderInfo(new OrderInfo(1, new Product(10.0, "name", true, new ProductCategory("cat", "", cafe),  cafe), "") , true, true, 1, "", "-1", "");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }


}
