package com.example.softcafeengineer.view.Manager.EditCategories;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;
import com.example.softcafeengineer.view.Manager.InfoInput.InfoInputPresenter;
import com.example.softcafeengineer.view.Manager.InfoInput.InfoInputViewStub;

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
    @After
    public void tearDown(){
        presenter = null;
        view = null;
    }
    @Test
    public void testDisabledEditButtonCategory(){
        presenter.onEditCategory(false, true, "edit_category", "description", "","");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testEditExistingCategory(){
        menuDAO.saveCategory(new ProductCategory("edit_category", "description", cafe));
        presenter.onEditCategory(true, true, "category", "description", "edit_category", "description");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different category name.");
    }
    @Test
    public void testDisabledEditButtonProduct(){
        presenter.onEditProduct(new Product(), false, true, "product", 10, "", "10");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testEditExistingProduct(){
        presenter.onAddProduct(true, "product","10", true);
        presenter.onAddProduct(true, "edit_product","10", true);
        presenter.onEditProduct(new Product(), true, true, "edit_username", Double.parseDouble("10"), "product", "10");
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different product name.");
    }
    @Test
    public void testDisabledAddButtonProduct(){
        presenter.onAddProduct(false, "product", "10", true);
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testAddExistingProduct(){
        presenter.onAddProduct(true, "product", "10", true);
        presenter.onAddProduct(true, "product", "10", true);
        Assert.assertEquals(view.getErrorTitle(), "Name is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different product name.");
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
    public void testGetProductResults(){
        Assert.assertEquals(presenter.getProductResults(), menuDAO.findAllProducts(cafe.getBrand()));

    }

}
