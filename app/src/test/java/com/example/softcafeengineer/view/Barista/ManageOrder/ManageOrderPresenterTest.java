package com.example.softcafeengineer.view.Barista.ManageOrder;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.Status;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;

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
    public void setUp(){
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        baristaDAO = new BaristaDAOMemory();
        view = new ManageOrderViewStub();
        presenter = new ManageOrderPresenter();
        barista = new Barista("barista", "password");
        cafeteria = new Cafeteria("Address", "0123456789", "123456789", "brand");
        table = new Table("QR", 1, cafeteria);
        barista.setCafe(cafeteria);
        baristaDAO.save(barista);

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
}
