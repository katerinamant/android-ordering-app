package com.example.softcafeengineer.view.Manager.EditMenu;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditMenuPresenterTest {
    private EditMenuPresenter presenter;
    private EditMenuViewStub view;
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafe;
    /**
     *  initializing all the objects needed to run tests for the
     *  EditMenuPresenter methods
     */
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
    /**
     *  testing whether the onAddCategory method shows the correct toast
     *  message when the add button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledAddButton(){
        presenter.onAddCategory(false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    /**
     *  testing whether the onAddCategory method shows the correct error title and
     *  error message when the user tries to set the category name to a name that is
     *  already taken by another category
     */
    @Test
    public void testAddExistingCategory(){
        presenter.onAddCategory(true, "category", "description");
        presenter.onAddCategory(true, "category","description");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different category name.");
    }
    /**
     *  testing whether the method getMenuDAO returns the correct
     *  MenuDAO object
     */
    @Test
    public void testGetMenuDAO(){
        Assert.assertEquals(presenter.getMenuDAO(), menuDAO);
    }
    /**
     *  testing whether the method getCafeteriaDAO returns the correct
     *  CafeteriaDAO object
     */
    @Test
    public void testGetCafeteriaDAO(){
        Assert.assertEquals(presenter.getCafeteriaDAO(), cafeteriaDAO);
    }
    /**
     *  testing whether the method getBrand returns the correct
     *  brand of this cafeteria
     */
    @Test
    public void testGetBrand(){
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }
    /**
     *  testing whether the method getCategoryResults returns all the categories
     *  that this cafeteria has
     */
    @Test
    public void testGetCategoryResults(){
        Assert.assertEquals(presenter.getCategoryResults(), menuDAO.findAllCategories(cafe.getBrand()));
    }

}
