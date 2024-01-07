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
    private RevenueDAO revenueDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafeteria;
    @Before
    public void setUp(){
        view = new ManagerRevenueViewStub();
        revenueDAO = new RevenueDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        cafeteria = new Cafeteria();
        cafeteria.setBrand("cafe_brand");
        cafeteriaDAO.save(cafeteria);

        presenter = new ManagerRevenuePresenter(view, cafeteria.getBrand(), revenueDAO);
    }
    @Test
    public void testDisabledButton(){
        presenter.onCalculate(false, 2024,1,1);
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }

    @Test
    public void testNoData(){
        presenter.onCalculate(true, 2024, 1, 1);
        Assert.assertEquals(view.getErrorTitle(),"Unable to retrieve data.");
        Assert.assertEquals(view.getErrorMessage(), "Please try again later.");
    }

}
