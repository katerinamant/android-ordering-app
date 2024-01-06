package com.example.softcafeengineer.view.Barista.Actions;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaristaActionsPresenterTest {
    private BaristaActionsPresenter presenter;
    private BaristaActionsViewStub view;
    private ActiveOrdersDAO activeOrdersDAO;
    private MonthlyRevenueDAO monthlyRevenueDAO;
    private Cafeteria cafe;
    @Before
    public void setUp(){
        view = new BaristaActionsViewStub();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        monthlyRevenueDAO = new MonthlyRevenueDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");

        presenter = new BaristaActionsPresenter();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setMonthlyRevenueDAO(monthlyRevenueDAO);
        presenter.setBrand(cafe.getBrand());
        presenter.setView(view);
    }
    @Test
    public void testGetOrderResults(){
        Assert.assertEquals(presenter.getOrderResults(), activeOrdersDAO.findAll(presenter.getBrand()));
    }
    @Test
    public void testGetBrand(){
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }
    @Test
    public void testGetMonthlyRevenueDAO(){
        Assert.assertEquals(presenter.getMonthlyRevenueDAO(), monthlyRevenueDAO);
    }
    @Test
    public void testGetActiveOrdersDAO(){
        Assert.assertEquals(presenter.getActiveOrdersDAO(), activeOrdersDAO);
    }
}
