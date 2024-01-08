package com.example.softcafeengineer.view.Manager.Revenue;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerRevenuePresenterTest {
    private ManagerRevenuePresenter presenter;
    private ManagerRevenueViewStub view;
    private RevenueDAO monthlyRevenueDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafeteria;

    /**
     * initializing all the objects needed to run tests for the
     * ManageRevenuePresenter methods
     */
    @Before
    public void setUp() {
        view = new ManagerRevenueViewStub();
        monthlyRevenueDAO = new RevenueDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        cafeteria = new Cafeteria();
        cafeteria.setBrand("cafe_brand");
        cafeteriaDAO.save(cafeteria);

        presenter = new ManagerRevenuePresenter(view, cafeteria.getBrand(), monthlyRevenueDAO);
    }

    /**
     * testing whether the onCalculate method shows the correct toast
     * message when the calculate button is disabled
     */
    @Test
    public void testDisabledButton() {
        presenter.onCalculate(false, 2024, 1, 1);
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }

    /**
     * testing whether the onCalculate method shows the correct toast
     * message when there is not data to retrieve from that date
     */
    @Test
    public void testNoData() {
        presenter.onCalculate(true, 2024, 1, 1);
        Assert.assertEquals(view.getErrorTitle(), "Unable to retrieve data.");
        Assert.assertEquals(view.getErrorMessage(), "Please try again later.");
    }

}
