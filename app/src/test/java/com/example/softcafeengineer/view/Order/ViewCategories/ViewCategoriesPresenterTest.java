package com.example.softcafeengineer.view.Order.ViewCategories;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ViewCategoriesPresenterTest {
    private ViewCategoriesPresenter presenter;
    private ViewCategoriesViewStub view;
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private ActiveCartsDAO activeCartsDAO;
    private ProductCategory category;
    private Cafeteria cafe;
    private Order order;
    private Product product;

    @Before
    public void setUp() throws InvalidDateException {
        view = new ViewCategoriesViewStub();
        menuDAO = new MenuDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        cafe = new Cafeteria("address", "0123456789", "123456789", "cafe_brand");
        category = new ProductCategory("cat", "description", cafe);
        order = new Order(new Date(1, 1, 2024), new Table("QRCode", 15, cafe));
        cafeteriaDAO.save(cafe);
        menuDAO.saveCategory(category);
        activeCartsDAO = new ActiveCartsDAOMemory();

        presenter = new ViewCategoriesPresenter();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        presenter.setMenuDAO(menuDAO);
        presenter.setActiveCartsDAO(activeCartsDAO);
        presenter.setView(view, category.getName(), cafe.getBrand(), order.getTable().getQRCode());
    }

    /**
     * testing whether the method getMenuDAO returns the correct
     * MenuDAO object
     */
    @Test
    public void testGetMenuDAO() {
        Assert.assertEquals(presenter.getMenuDAO(), menuDAO);
    }

    /**
     * testing whether the method getCafeteriaDAO returns the correct
     * CafeteriaDAO object
     */
    @Test
    public void testGetCafeteriaDAO() {
        Assert.assertEquals(presenter.getCafeteriaDAO(), cafeteriaDAO);
    }

    /**
     * testing whether the method getActiveCartsDAO returns the correct
     * ActiveCartsDAO object
     */
    @Test
    public void testGetActiveCartsDAO() {
        Assert.assertEquals(presenter.getActiveCartsDAO(), activeCartsDAO);
    }

    /**
     * testing whether the method getCategoryName returns the correct
     * name of this category
     */
    @Test
    public void testGetCategoryName() {
        Assert.assertEquals(presenter.getCategoryName(), category.getName());
    }

    /**
     * testing whether the method getCategoryDesc returns the correct
     * description of this category
     */
    @Test
    public void testGetCategoryDesc() {
        Assert.assertEquals(presenter.getCategoryDesc(), category.getDescription());
    }

    /**
     * testing whether the method getProductResults returns all the products
     * belonging to this category
     */
    @Test
    public void testGetProductResults() {
        Assert.assertEquals(presenter.getProductResults(), menuDAO.findAllProductsOfCategory(cafe.getBrand(), category));
    }

    /**
     * testing whether the onConfirmAddToCart method shows the correct toast
     * message when the confirm button is disabled, meaning some of the
     * required fields were left empty
     */
    @Test
    public void testDisabledConfirmButton() {
        presenter.onConfirmAddToCart(new Product(), false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }

    /**
     * testing whether the onConfirmAddToCart method shows the correct error title and
     * error message when the quantity is set to an invalid value (zero)
     */
    @Test
    public void testInvalidQuantity() {
        presenter.onConfirmAddToCart(new Product(), true, "0", "comments");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }
}
