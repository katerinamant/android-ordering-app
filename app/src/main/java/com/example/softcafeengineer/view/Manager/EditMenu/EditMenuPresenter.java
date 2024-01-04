package com.example.softcafeengineer.view.Manager.EditMenu;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class EditMenuPresenter
{
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private EditMenuView view;
    private String cafe_brand;
    private List<Product> productResults = new ArrayList<Product>();
    private List<ProductCategory> categoryResults = new ArrayList<ProductCategory>();
    public void setMenuDAO(MenuDAO menuDAO)
    {
        this.menuDAO = menuDAO;
    }
    public MenuDAO getMenuDAO()
    {
        return this.menuDAO;
    }
    public void setCafeteriaDAO(CafeteriaDAO cafeteriaDAO)
    {
        this.cafeteriaDAO = cafeteriaDAO;
    }
    public CafeteriaDAO getCafeteriaDAO()
    {
        return this.cafeteriaDAO;
    }
    public void setBrandP(String brand)
    {
        this.cafe_brand = brand;
        this.findAllProducts(this.cafe_brand);
    }
    public String getBrand()
    {
        return this.cafe_brand;
    }
    public void setView(EditMenuView view)
    {
        this.view = view;
    }
    public void findAllProducts(String brand)
    {
        this.productResults.clear();
        this.productResults = menuDAO.findAllProducts(brand);
    }
    public List<ProductCategory> findAllCategories(String brand)
    {
        this.categoryResults.clear();
        this.categoryResults = menuDAO.findAllCategories(brand);
        return categoryResults;
    }
    public List<Product> getProductResults()
    {
        return this.productResults;
    }

    public void onAddProduct(boolean add_product_enabled, String name, ProductCategory category, boolean available, double price){
        if (!add_product_enabled){
            view.showToast("Please fill all the required fields.");
        } else {
            if (this.menuDAO.productExists(name)){
                view.showError("Name is taken.", "Please provide a different name.");
            } else {
                Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
                Product product = new Product(price, name, available, category, cafe);
                menuDAO.saveProduct(product);
                view.successfulNewProduct();
            }
        }
    }

    public void onAddCategory(boolean addCategoryEnabled, String name, String description) {
        if (!addCategoryEnabled){
            view.showToast("Please fill all the required fields.");
        } else {
            if (this.menuDAO.categoryExists(name)){
                view.showError("Name is taken.", "Please provide a different name.");
            }else{
                Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
                ProductCategory category = new ProductCategory(name, description, cafe);
                menuDAO.saveCategory(category);
                view.successfulNewCategory();
            }
        }
    }

    public List<ProductCategory> getCategoryResults() {
        return this.categoryResults;
    }
}
