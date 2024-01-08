package com.example.softcafeengineer.view.Barista.LogIn;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaristaLogInPresenterTest {
    private BaristaLogInViewStub view;
    private BaristaLogInPresenter presenter;
    private BaristaDAO baristaDAO;

    /**
     * initializing all the objects needed to run tests for the
     * BaristaLogInPresenter methods
     */
    @Before
    public void setUp() {
        view = new BaristaLogInViewStub();
        baristaDAO = new BaristaDAOMemory();
        presenter = new BaristaLogInPresenter(view, baristaDAO);
    }

    /**
     * testing whether the onLogin method shows the correct toast
     * message when the login button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledLogInButton() {
        presenter.onLogin(false, "barista", "password");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }

    /**
     * testing whether the method onLogin shows the correct error tittle and
     * error message when the user provides a wrong username
     */
    @Test
    public void testWrongUsername() {
        Barista b = new Barista("barista", "password");
        b.setCafe(new Cafeteria());
        baristaDAO.save(b);
        presenter.onLogin(true, "wrong_barista", "password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }

    /**
     * testing whether the method onLogin shows the correct error tittle and
     * message when the user provides a wrong password
     */
    @Test
    public void testWrongPassoword() {
        Barista b = new Barista("barista", "password");
        b.setCafe(new Cafeteria());
        baristaDAO.save(b);
        presenter.onLogin(true, "barista", "wrong_password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }
}
