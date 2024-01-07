package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfoInputPresenterTest {
    private InfoInputPresenter presenter;
    private InfoInputViewStub view;
    private CafeteriaDAO cafeteriaDAO;
    private ManagerDAO managerDAO;
    private MonthlyRevenueDAO monthlyRevenueDAO;
    private Cafeteria cafeteria;

    @Before
    public void setUp(){
        view = new InfoInputViewStub();
        managerDAO = new ManagerDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        monthlyRevenueDAO = new MonthlyRevenueDAOMemory();
        cafeteria = new Cafeteria();
        cafeteria.setBrand("cafe_brand");
        cafeteriaDAO.save(cafeteria);

        presenter = new InfoInputPresenter(view, managerDAO, cafeteriaDAO, monthlyRevenueDAO);
    }
    @After
    public void tearDown(){
        presenter=null;
        view=null;
    }
    @Test
    public void testDisabledButton(){
        presenter.onFinish(false, "","","","","","");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testShortPhoneNumber(){
        presenter.onFinish(true, "user", "password", "address", "0", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }
    @Test
    public void testShortSSN(){
        presenter.onFinish(true, "user", "password", "address", "0123456789", "1", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid SSN. Must contain 9 characters.");
    }
    @Test
    public void testLongPhoneNumber(){
        presenter.onFinish(true, "user", "password", "address", "01234567890", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }
    @Test
    public void testLongSSN(){
        presenter.onFinish(true, "user", "password", "address", "0123456789", "0123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid SSN. Must contain 9 characters.");
    }
    @Test
    public void testCafeExists(){
        presenter.onFinish(true, "user", "password", "address", "0123456789", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "This brand is already in use.");
    }
}
