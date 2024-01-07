package com.example.softcafeengineer.view.Manager.EditCategories;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EditCategoriesPresenterTest {
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafe;
    private EditCategoriesViewStub view;
    private EditCategoriesPresenter presenter;
    private ProductCategory category;

    /**
     *  initializing all the objects needed to run tests for the
     *  EditCategoriesPresenter methods
     */
    @Before
    public void setUp(){
        view = new EditCategoriesViewStub();
        cafeteriaDAO = new CafeteriaDAOMemory();
        menuDAO = new MenuDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");
        cafeteriaDAO.save(cafe);
        category = new ProductCategory("category", "description", cafe);
        menuDAO.saveCategory(category);

        presenter = new EditCategoriesPresenter();
        presenter.setMenuDAO(menuDAO);
        presenter.setCafeteriaDAO(cafeteriaDAO);
        presenter.setBrand(cafe.getBrand());
        presenter.setView(view, "category", "cafe_brand");
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
     *  testing whether the method getCategoryName returns the correct
     *  name of this category
     */
    @Test
    public void testGetCategoryName(){
        Assert.assertEquals(presenter.getCategoryName(), category.getName());
    }
    /**
     *  testing whether the method getCategoryDesc returns the correct
     *  description of this category
     */
    @Test
    public void testGetCategoryDescription(){
        Assert.assertEquals(presenter.getCategoryDesc(), category.getDescription());
    }
    /**
     *  testing whether the method getProductResults returns all the products
     *  belonging to this category
     */
    @Test
    public void testGetProductResults(){
        Assert.assertEquals(presenter.getProductResults(), menuDAO.findAllProducts(cafe.getBrand()));
    }
    /**
     *  testing whether the onEditCategory method shows the correct toast
     *  message when the confirm button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledConfirmButtonCategory(){
        presenter.onEditCategory(false, true, "edit_category", "description", "","");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    /**
     *  testing whether the onEditCategory method shows the correct error title and
     *  error message when the user tries to change the category name to a name that is
     *  already taken by another category
     */
    @Test
    public void testEditExistingCategory(){
        menuDAO.saveCategory(new ProductCategory("edit_category", "description", cafe));
        presenter.onEditCategory(true, true, "category", "description", "edit_category", "description");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different category name.");
    }
    /**
     *  testing whether the onEditProduct method shows the correct toast
     *  message when the confirm button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledCofirmButtonProduct(){
        presenter.onEditProduct(new Product(), false, true, "product", 10, "", "10");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    /**
     *  testing whether the onEditProduct method shows the correct error title and
     *  error message when the user tries to change the product name to a name that is
     *  already taken by another product
     */
    @Test
    public void testEditExistingProduct(){
        presenter.onAddProduct(true, "product","10", true);
        presenter.onAddProduct(true, "edit_product","10", true);
        presenter.onEditProduct(new Product(), true, true, "edit_username", Double.parseDouble("10"), "product", "10");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different product name.");
    }
    /**
     *  testing whether the onAddProduct method shows the correct toast
     *  message when the add button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledAddButtonProduct(){
        presenter.onAddProduct(false, "product", "10", true);
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    /**
     *  testing whether the onAddProduct method shows the correct error title and
     *  error message when the user tries to set the product name to a name that is
     *  already taken by another product
     */
    @Test
    public void testAddExistingProduct(){
        presenter.onAddProduct(true, "product", "10", true);
        presenter.onAddProduct(true, "product", "10", true);
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different product name.");
    }

    /**
     *  testing whether the onDeleteCategory method successfully deletes a category from menuDAO
     */
    @Test
    public void testOnDeleteCategory(){
        presenter.onDeleteCategory();
        Assert.assertEquals(presenter.getMenuDAO().categoryExists(cafe.getBrand(), category.getName()), menuDAO.categoryExists(cafe.getBrand(), category.getName()));
    }

}
