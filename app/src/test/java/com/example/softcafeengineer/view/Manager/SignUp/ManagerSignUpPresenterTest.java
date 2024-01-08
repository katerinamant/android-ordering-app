package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerSignUpPresenterTest {
    private ManagerSignUpPresenter presenter;
    private ManagerSignUpViewStub view;
    private ManagerDAO managerDAO;

    /**
     * initializing all the objects needed to run tests for the
     * ManagerSignUpPresenter methods
     */
    @Before
    public void setUp() {
        view = new ManagerSignUpViewStub();
        managerDAO = new ManagerDAOMemory();

        presenter = new ManagerSignUpPresenter(view, managerDAO);
    }

    /**
     * testing whether the onContinue method shows the correct toast message
     * when a user tries to add a password shorter than 8 characters
     */
    @Test
    public void testAddShortPassword() {
        presenter.onContinue(true, "short_password_username", "1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }

    /**
     * testing whether the onContinue method shows the correct toast message
     * when a user tries to add a new user with a username that is already taken
     */
    @Test
    public void testAddExistingUsername() {
        presenter.onContinue(true, "manager_username", "password");
        presenter.onContinue(true, "manager_username", "password");
        Assert.assertEquals(view.getToastMessage(), "This username is already in use.");
    }

    /**
     * testing whether the onContinue method shows the correct toast
     * message when the continue button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledButton() {
        presenter.onContinue(false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }
}
