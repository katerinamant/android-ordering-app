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

    /**
     * initializing all the objects needed to run tests for the
     * ManagerLogInPresenter methods
     */
    @Before
    public void setUp() {
        view = new ManagerLogInViewStub();
        managerDAO = new ManagerDAOMemory();

        presenter = new ManagerLogInPresenter(view, managerDAO);
        user = new User("login_username", "password");
        managerDAO.save(user);
    }

    /**
     * testing whether the method onLogin shows the correct error tittle and
     * message when the user provides a wrong password
     */
    @Test
    public void testWrongPassword() {
        presenter.onLogin(true, "login_username", "wrong_password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }

    /**
     * testing whether the method onLogin shows the correct error tittle and
     * error message when the user provides a wrong username
     */
    @Test
    public void testWrongUsername() {
        presenter.onLogin(true, "wrong_username", "password");
        Assert.assertEquals(view.getErrorTitle(), "Log In unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The credentials provided were invalid. Try again.");
    }

    /**
     * testing whether the onLogin method shows the correct toast
     * message when the login button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledLogInButton() {
        presenter.onLogin(false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }

}
