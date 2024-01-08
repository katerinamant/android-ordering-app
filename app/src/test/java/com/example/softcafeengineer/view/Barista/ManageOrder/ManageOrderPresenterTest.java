package com.example.softcafeengineer.view.Barista.ManageOrder;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.Status;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageOrderPresenterTest {
    private ActiveOrdersDAO activeOrdersDAO;
    private BaristaDAO baristaDAO;
    private ManageOrderViewStub view;
    private ManageOrderPresenter presenter;
    private Barista barista;
    private Cafeteria cafeteria;
    private Table table;
    private Order order;
    private RevenueDAO revenueDAO;

    /**
     * initializing all the objects needed to run tests for the
     * ManageOrderPresenter methods
     */
    @Before
    public void setUp() throws InvalidDateException {
        revenueDAO = new RevenueDAOMemory();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        baristaDAO = new BaristaDAOMemory();
        view = new ManageOrderViewStub();
        barista = new Barista("barista", "password");
        cafeteria = new Cafeteria("Address", "0123456789", "123456789", "brand");
        table = new Table("QR", 1, cafeteria);
        barista.setCafe(cafeteria);
        baristaDAO.save(barista);
        order = new Order(new Date(1, 1, 2024), table);
        activeOrdersDAO.save(order);
        revenueDAO.addCafeteria(cafeteria.getBrand());

        presenter = new ManageOrderPresenter();
        presenter.setRevenueDAO(revenueDAO);
        presenter.setBaristaDAO(baristaDAO);
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setView(view, barista.getUsername(), barista.getPassword(), cafeteria.getBrand(), table.getId());
    }

    /**
     * testing whether the method getRevenueDAO returns the correct
     * RevenueDAO object
     */
    @Test
    public void testGetRevenueDAO() {
        Assert.assertEquals(presenter.getRevenueDAO(), revenueDAO);
    }

    /**
     * testing whether the method getActiveOrdersDAO returns the correct
     * ActiveOrdersDAO object
     */
    @Test
    public void testGetActiveOrdersDAO() {
        Assert.assertEquals(presenter.getActiveOrdersDAO(), activeOrdersDAO);
    }

    /**
     * testing whether the method getBaristaDAO returns the correct
     * BaristaDAO object
     */
    @Test
    public void testGetBaristaDAO() {
        Assert.assertEquals(presenter.getBaristaDAO(), baristaDAO);
    }

    /**
     * testing whether the method getTotalCost returns the correct
     * totalCost of this order
     */
    @Test
    public void testGetTotalCost() {
        Assert.assertEquals(presenter.getTotalCost(), order.getTotalCost(), 0.0);
    }

    /**
     * testing whether the method getOrderStatus returns the correct
     * Status of this order
     */
    @Test
    public void testGetOrderStatus() {
        Assert.assertEquals(presenter.getOrderStatus(), order.getOrderStatus().toString());
    }

    /**
     * testing whether the method getOrderResults get returns the correct
     * OrderInfo for this order
     */
    @Test
    public void testGetOrderResults() {
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }

    /**
     * testing whether the method onWaitingStatus shows the correct error title
     * and error message when it is called
     */
    @Test
    public void testOnWaitingStatus() {
        presenter.onWaitingStatus();
        Assert.assertEquals(view.getErrorTitle(), "Invalid change.");
        Assert.assertEquals(view.getErrorMessage(), "Please choose a different status.");
    }

    /**
     * testing whether the onEditProductInfo method shows the correct toast
     * message when the confirm button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledConfirmButton() {
        presenter.onEditProductInfo(new OrderInfo(), false, true, 2, "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }

    /**
     * testing whether the method onEditProductInfo shows the correct error tittle and
     * error message when the user provides a negative quantity value
     */
    @Test
    public void testNotValidQuantity() {
        presenter.onEditProductInfo(new OrderInfo(3, new Product(), "description"), true, true, 3, "-1");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }

    /**
     * testing whether the method onEditProductInfo shows the correct error tittle and
     * error message when the user provides a quantity value bigger than the previous one
     */
    @Test
    public void testBiggerQuantity() {
        presenter.onEditProductInfo(new OrderInfo(3, new Product(), "description"), true, true, 3, "4");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), String.format("Please provide a number smaller than or equal to the previous quantity (%d).", 3));
    }

    /**
     * testing whether the onCompletedStatus shows the correct error title and error
     * message when the order is not in progress
     */
    @Test
    public void testErrorOnCompletedStatus() {
        order.setOrderStatus(Status.WAITING);
        presenter.onCompletedStatus();
        Assert.assertEquals(view.getErrorTitle(), "Invalid change.");
        Assert.assertEquals(view.getErrorMessage(), "Please choose a different status.");
    }

    /**
     * testing whether the method onCompletedStatus successfully marks an order as completed
     * and removes it from the active orders
     */
    @Test
    public void testSuccessfulOnCompletedStatus() {
        order.setOrderStatus(Status.IN_PROGRESS);
        presenter.onCompletedStatus();
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }

    /**
     * testing whether the method onCanceledStatus successfully marks an order as cancelled
     */
    @Test
    public void testSuccessfulOnCanceledStatus() {
        order.setOrderStatus(Status.IN_PROGRESS);
        presenter.onCanceledStatus();
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }
}
