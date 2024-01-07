package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.view.Manager.SignUp.ManagerSignUpPresenter;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerSignUpPresenterTest {
    private ManagerSignUpPresenter presenter;
    private ManagerSignUpViewStub view;
    private ManagerDAO managerDAO;
    @Before
    public void setUp(){
        view = new ManagerSignUpViewStub();
        managerDAO = new ManagerDAOMemory();

        presenter = new ManagerSignUpPresenter(view, managerDAO);
    }
    @Test
    public void testAddShortPassword(){
        presenter.onContinue(true, "short_password_username","1");
        Assert.assertEquals(view.getToastMessage(), "Your password must have a minimum length of 8 characters.");
    }
    @Test
    public void testAddExistingUsername(){
        presenter.onContinue(true, "manager_username", "password");
        presenter.onContinue(true, "manager_username", "password");
        Assert.assertEquals(view.getToastMessage(), "This username is already in use.");
    }
    @Test
    public void testDisabledButton(){
        presenter.onContinue(false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required fields.");
    }
}
