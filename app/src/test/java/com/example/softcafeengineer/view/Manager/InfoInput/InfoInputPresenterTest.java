package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfoInputPresenterTest {
    private InfoInputPresenter presenter;
    private InfoInputViewStub view;
    private CafeteriaDAO cafeteriaDAO;
    private ManagerDAO managerDAO;
    private RevenueDAO revenueDAO;
    private Cafeteria cafeteria;

    /**
     * initializing all the objects needed to run tests for the
     * InfoInputPresenter methods
     */
    @Before
    public void setUp() {
        view = new InfoInputViewStub();
        managerDAO = new ManagerDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        revenueDAO = new RevenueDAOMemory();
        cafeteria = new Cafeteria();
        cafeteria.setBrand("cafe_brand");
        cafeteriaDAO.save(cafeteria);

        presenter = new InfoInputPresenter(view, managerDAO, cafeteriaDAO, revenueDAO);
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the finish button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledFinishButton() {
        presenter.onFinish(false, "", "", "", "", "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the user tries to set a phone number shorter than 10 digits
     */
    @Test
    public void testShortPhoneNumber() {
        presenter.onFinish(true, "user", "password", "address", "0", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the user tries to set a ssn shorter than 9 digits
     */
    @Test
    public void testShortSSN() {
        presenter.onFinish(true, "user", "password", "address", "0123456789", "1", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid TIN. Must contain 9 characters.");
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the user tries to set a phone number longer than 10 digits
     */
    @Test
    public void testLongPhoneNumber() {
        presenter.onFinish(true, "user", "password", "address", "01234567890", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid phone number. Must contain 10 characters.");
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the user tries to set a ssn longer than 9 digits
     */
    @Test
    public void testLongSSN() {
        presenter.onFinish(true, "user", "password", "address", "0123456789", "0123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "Invalid TIN. Must contain 9 characters.");
    }

    /**
     * testing whether the onFinish method shows the correct toast
     * message when the user tries to set the cafeteria brand to a brand that is
     * already taken by another cafeteria
     */
    @Test
    public void testCafeExists() {
        presenter.onFinish(true, "user", "password", "address", "0123456789", "123456789", "cafe_brand");
        Assert.assertEquals(view.getToastMessage(), "This brand is already in use.");
    }
}
