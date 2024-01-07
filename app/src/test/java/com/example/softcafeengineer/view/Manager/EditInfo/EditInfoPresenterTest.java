package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditInfoPresenterTest {
    private  EditInfoPresenter presenter;
    private EditInfoViewStub view;
    private CafeteriaDAO cafeteriaDAO;
    private MonthlyRevenueDAO monthlyRevenueDAO;
    private Cafeteria cafeteria;
    private TableDAO tableDAO;
    private MenuDAO menuDAO;
    private BaristaDAO baristaDAO;
    private ActiveOrdersDAO activeOrdersDAO;
    @Before
    public void setUp(){
        view = new EditInfoViewStub();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        tableDAO = new TableDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        cafeteria = new Cafeteria();
        cafeteria.setBrand("cafe_brand");
        cafeteriaDAO.save(cafeteria);
        monthlyRevenueDAO = new MonthlyRevenueDAOMemory();
        menuDAO = new MenuDAOMemory();
        baristaDAO = new BaristaDAOMemory();

        presenter = new EditInfoPresenter(view, cafeteria.getBrand(), activeOrdersDAO, baristaDAO, cafeteriaDAO, menuDAO, monthlyRevenueDAO, tableDAO);
    }
    @After
    public void tearDown(){
        presenter=null;
        view=null;
    }
    @Test
    public void testDisabledAddButton(){
        presenter.onFinish(false, true, "cafe_brand", "1234567890", "123456789", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testShortPhoneNumber(){
        presenter.onFinish(true, true, "cafe_brand", "0", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }
    @Test
    public void testShortSSN(){
        presenter.onFinish(true, true, "cafe_brand", "1234567890", "0", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid SSN. Must contain 9 characters.");
    }
    @Test
    public void testLongPhoneNumber(){
        presenter.onFinish(true, true, "cafe_brand", "01234567890", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }
    @Test
    public void testLongSSN(){
        presenter.onFinish(true, true, "cafe_brand", "1234567890", "0123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid SSN. Must contain 9 characters.");
    }
    @Test
    public void testCafeExists(){
        presenter.onFinish(true, true, "cafe_brand1", "1234567890", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "This brand is already in use.");
    }

}
