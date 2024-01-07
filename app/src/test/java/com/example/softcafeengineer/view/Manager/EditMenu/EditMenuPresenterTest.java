package com.example.softcafeengineer.view.Manager.EditMenu;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditMenuPresenterTest {
    private EditMenuPresenter presenter;
    private EditMenuViewStub view;
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafe;
    @Before
    public void setUp(){
        view = new EditMenuViewStub();
        menuDAO = new MenuDAOMemory();

        cafeteriaDAO = new CafeteriaDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");
        cafeteriaDAO.save(cafe);

        presenter = new EditMenuPresenter();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        presenter.setView(view);
        presenter.setMenuDAO(menuDAO);
        presenter.setBrand(cafe.getBrand());
    }
    @Test
    public void testDisabledAddButton(){
        presenter.onAddCategory(false, "category", "description");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testAddExistingCategory(){
        presenter.onAddCategory(true, "category", "description");
        presenter.onAddCategory(true, "category","description");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different category name.");
    }
    @Test
    public void testGetMenuDAO(){
        Assert.assertEquals(presenter.getMenuDAO(), menuDAO);
    }
    @Test
    public void testGetCafeteriaDAO(){
        Assert.assertEquals(presenter.getCafeteriaDAO(), cafeteriaDAO);
    }
    @Test
    public void testGetBrand(){
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }
    @Test
    public void testGetCategoryResults(){
        Assert.assertEquals(presenter.getCategoryResults(), menuDAO.findAllCategories(cafe.getBrand()));
    }

}
