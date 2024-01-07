package com.example.softcafeengineer.view.Order.ViewCategories;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Date;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ViewCategoriesPresenterTest {
    private ViewCategoriesPresenter presenter;
    private ViewCategoriesViewStub view;
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private ActiveOrdersDAO activeOrdersDAO;
    private ProductCategory category;
    private Cafeteria cafe;
    private Order order;
    private Product product;
    @Before
    public void setUp() throws InvalidDateException {
        view = new ViewCategoriesViewStub();
        menuDAO = new MenuDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        activeOrdersDAO = new ActiveOrdersDAOMemory();
        cafe = new Cafeteria("address", "0123456789", "123456789", "cafe_brand");
        category = new ProductCategory("cat", "description", cafe);
        order = new Order(new Date(1,1,2024), new Table("QRCode", 15, cafe));
        cafeteriaDAO.save(cafe);
        menuDAO.saveCategory(category);

        presenter = new ViewCategoriesPresenter();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        presenter.setMenuDAO(menuDAO);
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        presenter.setView(view, category.getName(), cafe.getBrand(), order.getTable().getQRCode());
    }
    @After
    public void tearDown(){
        presenter = null;
        view = null;
    }
    @Test
    public void testGetCategoryName(){
        Assert.assertEquals(presenter.getCategoryName(), category.getName());
    }
    @Test
    public void testGetCategoryDescription(){
        Assert.assertEquals(presenter.getCategoryDesc(), category.getDescription());
    }
    @Test
    public void testGetProductResults(){
        Assert.assertEquals(presenter.getProductResults(), menuDAO.findAllProductsOfCategory(cafe.getBrand(), category));
    }
    @Test
    public void testDisabledConfirmButton(){
        presenter.onConfirmAddToCart(new Product(), false, "", "");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    @Test
    public void testInvalidQuantity(){
        presenter.onConfirmAddToCart(new Product(), true, "0", "comments");
        Assert.assertEquals(view.getErrorTitle(), "Invalid input.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a valid quantity.");
    }
}
