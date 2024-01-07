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
    @Before
    public void setUp(){
        view = new BaristaLogInViewStub();
        baristaDAO = new BaristaDAOMemory();
        presenter = new BaristaLogInPresenter(view, baristaDAO);
    }
    @Test
    public void testDisabledAddButton(){
        presenter.onLogin(false, "barista", "password");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }
    @Test
    public void testWrongUsername(){
        Barista b = new Barista("barista", "password");
        b.setCafe(new Cafeteria());
        baristaDAO.save(b);
        presenter.onLogin(true, "wrong_barista", "password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }
    @Test
    public void testWrongPassowrd(){
        Barista b = new Barista("barista", "password");
        b.setCafe(new Cafeteria());
        baristaDAO.save(b);
        presenter.onLogin(true, "barista", "wrong_password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }
}
