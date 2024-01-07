package com.example.softcafeengineer.view.Manager.LogIn;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.User;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerLogInPresenterTest {
    ManagerLogInPresenter presenter;
    ManagerLogInViewStub view;
    ManagerDAO managerDAO;
    User user;

    @Before
    public void setUp(){
        view = new ManagerLogInViewStub();
        managerDAO = new ManagerDAOMemory();

        presenter = new ManagerLogInPresenter(view, managerDAO);
        user = new User("login_username", "password");
        managerDAO.save(user);
    }

    @Test
    public void testWrongPassword(){
        presenter.onLogin(true, "login_username","wrong_password");
        Assert.assertEquals(view.getErrorTitle(),"Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(),"The credentials provided were invalid. Try again.");
    }

    @Test
    public void testWrongUsername(){
        presenter.onLogin(true, "wrong_username","password");
        Assert.assertEquals(view.getErrorTitle(),"Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(),"The credentials provided were invalid. Try again.");
    }

    @Test
    public void testDisabledButton(){
        presenter.onLogin(false, "","");
        Assert.assertEquals(view.getToastMessage(),"Please fill the required fields.");
    }

}
