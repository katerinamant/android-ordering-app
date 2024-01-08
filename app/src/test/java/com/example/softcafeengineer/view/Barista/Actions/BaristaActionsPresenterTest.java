package com.example.softcafeengineer.view.Barista.Actions;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaristaActionsPresenterTest {
    private BaristaActionsPresenter presenter;
    private BaristaActionsViewStub view;
    private ActiveOrdersDAO activeOrdersDAO;
    private RevenueDAO revenueDAO;
    private Cafeteria cafe;

    /**
     * initializing all the objects needed to run tests for the
     * BaristaActionsPresenter methods
     */
    @Before
    public void setUp() {
        view = new BaristaActionsViewStub();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        revenueDAO = new RevenueDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");

        presenter = new BaristaActionsPresenter();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setRevenueDAO(revenueDAO);
        presenter.setBrand(cafe.getBrand());
        presenter.setView(view);
    }

    /**
     * testing whether the method getOrderResults returns correctly all
     * of the orders under this cafeteria brand
     */
    @Test
    public void testGetOrderResults() {
        // TODO: Update according to changes
        Assert.assertEquals(presenter.getOrderResults(), activeOrdersDAO.findAll(presenter.getBrand()));
    }

    /**
     * testing whether the method getBrand returns correctly the cafeteria
     * brand this BaristaActionsPresenter belongs to
     */
    @Test
    public void testGetBrand() {
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }

    // TODO: add test for get username

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
}
