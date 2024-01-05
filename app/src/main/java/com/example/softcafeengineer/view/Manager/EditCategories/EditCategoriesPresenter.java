package com.example.softcafeengineer.view.Manager.EditCategories;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuView;

import java.util.ArrayList;
import java.util.List;

public class EditCategoriesPresenter {
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private EditCategoriesView view;
    private String cafe_brand;
    private List<Product> productResults = new ArrayList<Product>();

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

    public void setBrand(String brand) {
        this.cafe_brand = brand;
        this.findAllProducts(this.cafe_brand);
    }
    public String getBrand()
    {
        return this.cafe_brand;
    }

    public void setView(EditCategoriesView view)
    {
        this.view = view;
    }

    public void findAllProducts(String brand) {
        this.productResults.clear();
        this.productResults = menuDAO.findAllProducts(brand);
    }
    public List<Product> getProductResults() {
        return this.productResults;
    }


    public void onAddProduct(boolean addProductEnabled, String name, double price, boolean availability, String category) {
        if (!addProductEnabled){
            view.showToast("Please fill all the required fields.");
        } else {
            if (this.menuDAO.productExists(this.cafe_brand, name)) {
                view.showError("Name is taken.", "Please provide a different category name.");
            } else {
                Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
                Product product = new Product(price, name,availability, ,cafe);
                menuDAO.saveProduct(product);
                view.successfulNewProduct();
            }
        }
    }
}
