package com.example.softcafeengineer.view.Manager.ManageEmployees;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageEmployeesPresenterTest {
    private  ManageEmployeesPresenter presenter;
    private  ManageEmployeesViewStub view;
    private BaristaDAO baristaDAO;
    private CafeteriaDAO cafeDAO;
    private Cafeteria cafe;

    @Before
    public void setUp(){
        view = new ManageEmployeesViewStub();
        baristaDAO = new BaristaDAOMemory();
        cafeDAO = new CafeteriaDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");
        cafeDAO.save(cafe);

        presenter = new ManageEmployeesPresenter();
        presenter.setBaristaDAO(baristaDAO);
        presenter.setCafeteriaDAO(cafeDAO);
        presenter.setView(view);
        presenter.setBrand(cafeDAO.find("cafe_brand").getBrand());
    }

    @Test
    public void testAddShortPassword(){
        presenter.onAddNewEmployee(true, "short_password_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }

    @Test
    public void testAddExistingUsername(){
        presenter.onAddNewEmployee(true, "existing_username", "12345678");
        presenter.onAddNewEmployee(true, "existing_username", "12345678");
        Assert.assertEquals(view.getToastMessage(), "This username is already in use.");
    }

    @Test
    public void testAddDisabledButton(){
        presenter.onAddNewEmployee(false, "","");
        Assert.assertEquals(view.getToastMessage(),"Please fill the required fields.");
    }

    @Test
    public void testEditExistingUsername(){
        presenter.onAddNewEmployee(true, "edit_username", "12345678");
        presenter.onAddNewEmployee(true, "username1", "12345678");
        presenter.onEditEmployee(new Barista(), true, true, "username1", "12345678", "edit_username", "12345678");
        Assert.assertEquals(view.getErrorTitle(),"Username is taken.");
        Assert.assertEquals(view.getErrorMessage(),"Please provide a different username.");
    }

    @Test
    public void testEditShortPassword(){
        Barista barista = new Barista("editp_username", "12345678");
        presenter.onEditEmployee( barista,true, true, "editp_username", "12345678", "editp_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }

    @Test
    public void testEditDisabledButton(){
        Barista barista = new Barista("editp_username", "12345678");
        presenter.onEditEmployee( barista,false, true, "", "", "editp_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }

    @Test
    public void testGetBaristaDAO(){
        Assert.assertEquals(presenter.getBaristaDAO(), baristaDAO);
    }

    @Test
    public void testGetCafeteriaDAO(){
        Assert.assertEquals(presenter.getCafeteriaDAO(), cafeDAO);
    }

    @Test
    public void testGetBrand(){
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }

    @Test
    public void testGetEmployeeResults(){
        Assert.assertEquals(presenter.getEmployeeResults(), baristaDAO.findAll(cafe.getBrand()));
    }
}
