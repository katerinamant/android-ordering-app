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
    private ManageEmployeesPresenter presenter;
    private ManageEmployeesViewStub view;
    private BaristaDAO baristaDAO;
    private CafeteriaDAO cafeDAO;
    private Cafeteria cafe;

    /**
     * initializing all the objects needed to run tests for the
     * ManageEmployeesPresenter methods
     */
    @Before
    public void setUp() {
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

    /**
     * testing whether the onAddNewEmployee method shows the correct toast message
     * when a manager tries to add a password shorter than 8 characters
     */
    @Test
    public void testAddShortPassword() {
        presenter.onAddNewEmployee(true, "short_password_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }

    /**
     * testing whether the onAddNewEmployee method shows the correct toast message
     * when a manager tries to add a new user with a username that is already taken
     */
    @Test
    public void testAddExistingUsername() {
        presenter.onAddNewEmployee(true, "existing_username", "12345678");
        presenter.onAddNewEmployee(true, "existing_username", "12345678");
        Assert.assertEquals(view.getToastMessage(), "This username is already in use.");
    }

    /**
     * testing whether the onAddNewEmployee method shows the correct toast
     * message when the confirm button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testAddDisabledConfirmButton() {
        presenter.onAddNewEmployee(false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }

    /**
     * testing whether the onEditEmployee method shows the correct error title and
     * error message when a manager tries to change a username to one that is already
     * taken by another user
     */
    @Test
    public void testEditExistingUsername() {
        presenter.onAddNewEmployee(true, "edit_username", "12345678");
        presenter.onAddNewEmployee(true, "username1", "12345678");
        presenter.onEditEmployee(new Barista(), true, true, "username1", "12345678", "edit_username", "12345678");
        Assert.assertEquals(view.getErrorTitle(), "Username is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different username.");
    }

    /**
     * testing whether the onEditEmployee method shows the correct toast message
     * when a manager tries to change a password to one shorter than 8 characters
     */
    @Test
    public void testEditShortPassword() {
        Barista barista = new Barista("editp_username", "12345678");
        presenter.onEditEmployee(barista, true, true, "editp_username", "12345678", "editp_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }

    /**
     * testing whether the onEditEmployee method shows the correct toast
     * message when the confirm button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testEditDisabledConfirmButton() {
        Barista barista = new Barista("editp_username", "12345678");
        presenter.onEditEmployee(barista, false, true, "", "", "editp_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
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
     * testing whether the method getCafeteriaDAO returns the correct
     * CafeteriaDAO object
     */
    @Test
    public void testGetCafeteriaDAO() {
        Assert.assertEquals(presenter.getCafeteriaDAO(), cafeDAO);
    }

    /**
     * testing whether the method getBrand returns the correct
     * brand of this cafeteria
     */
    @Test
    public void testGetBrand() {
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }

    /**
     * testing whether the method getEmployeeResults returns all the employees
     * working for this cafeteria
     */
    @Test
    public void testGetEmployeeResults() {
        Assert.assertEquals(presenter.getEmployeeResults(), baristaDAO.findAll(cafe.getBrand()));
    }
}
