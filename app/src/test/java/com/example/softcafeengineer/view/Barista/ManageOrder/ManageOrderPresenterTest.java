package com.example.softcafeengineer.view.Barista.ManageOrder;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
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

import org.junit.After;
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
    @Before
    public void setUp() throws InvalidDateException {
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        baristaDAO = new BaristaDAOMemory();
        view = new ManageOrderViewStub();
        presenter = new ManageOrderPresenter();
        barista = new Barista("barista", "password");
        cafeteria = new Cafeteria("Address", "0123456789", "123456789", "brand");
        table = new Table("QR", 1, cafeteria);
        barista.setCafe(cafeteria);
        baristaDAO.save(barista);
        order = new Order(new Date(1,1,2024), table);
        activeOrdersDAO.save(order);

        presenter.setBaristaDAO(baristaDAO);
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setView(view, barista.getUsername(), barista.getPassword(), cafeteria.getBrand(), table.getId());
    }

    @Test
    public void testGetActiveOrdersDAO(){
        Assert.assertEquals(presenter.getActiveOrdersDAO(), activeOrdersDAO);
    }
    @Test
    public void testGetBaristaDAO(){
        Assert.assertEquals(presenter.getBaristaDAO(), baristaDAO);
    }
    @Test
    public void testGetTotalCost(){
        Assert.assertEquals(presenter.getTotalCost(), order.getTotalCost(), 0.0);
    }
    @Test
    public void testGetOrderStatus(){
        Assert.assertEquals(presenter.getOrderStatus().toString(), order.getOrderStatus().toString());
    }
    @Test
    public void testGetOrderResults(){
        Assert.assertEquals(presenter.getOrderResults(), order.getOrderList());
    }
    @Test
    public void testDisabledEditButton(){
        presenter.onEditProductInfo(new OrderInfo(), false, true, 2, "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testNotValidQuantity(){
        presenter.onEditProductInfo(new OrderInfo(3, new Product(), "description"), true, true, 3, "-1");
        Assert.assertEquals(view.getErrorTitle(),"Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }
    @Test
    public void testBiggerQuantity(){
        presenter.onEditProductInfo(new OrderInfo(3, new Product(), "description"), true, true, 3, "4");
        Assert.assertEquals(view.getErrorTitle(),"Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), String.format("Please provide a number smaller than or equal to the previous quantity (%d).", 3));
    }
    @Test
    public void testOnWaitingStatus(){
        presenter.onWaitingStatus();
        Assert.assertEquals(view.getErrorTitle(), "Invalid change.");
        Assert.assertEquals(view.getErrorMessage(), "Please choose a different status.");
    }
    @Test
    public void testErrorOnCompletedStatus() {
        order.setOrderStatus(Status.WAITING);
        presenter.onCompletedStatus();
        Assert.assertEquals(view.getErrorTitle(), "Invalid change.");
        Assert.assertEquals(view.getErrorMessage(), "Please choose a different status.");
    }
    @Test
    public void testSuccessfulOnCompletedStatus() {
        order.setOrderStatus(Status.IN_PROGRESS);
        presenter.onCompletedStatus();
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }
    @Test
    public void testSuccessfulOnCanceledStatus() {
        order.setOrderStatus(Status.IN_PROGRESS);
        presenter.onCanceledStatus();
        Assert.assertEquals(presenter.getActiveOrdersDAO().find(order.getTable().getQRCode()), activeOrdersDAO.find(order.getTable().getQRCode()));
    }
}
