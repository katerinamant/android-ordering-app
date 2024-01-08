package com.example.softcafeengineer.view.Manager.EditMenu;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class EditMenuPresenter {
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private EditMenuView view;
    private String cafe_brand;
    private List<ProductCategory> categoryResults = new ArrayList<ProductCategory>();

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public MenuDAO getMenuDAO() {
        return this.menuDAO;
    }

    public void setCafeteriaDAO(CafeteriaDAO cafeteriaDAO) {
        this.cafeteriaDAO = cafeteriaDAO;
    }

    public CafeteriaDAO getCafeteriaDAO() {
        return this.cafeteriaDAO;
    }

    public void setBrand(String brand) {
        this.cafe_brand = brand;
        this.findAllCategories(this.cafe_brand);
    }

    public String getBrand() {
        return this.cafe_brand;
    }

    public void setView(EditMenuView view) {
        this.view = view;
    }

    public void findAllCategories(String brand) {
        this.categoryResults.clear();
        this.categoryResults = menuDAO.findAllCategories(brand);
    }

    public List<ProductCategory> getCategoryResults() {
        return this.categoryResults;
    }

    public void onAddCategory(boolean addCategoryEnabled, String name, String description) {
        if (!addCategoryEnabled) {
            view.showToast("Please fill all the required fields.");
            return;
        }

        if (this.menuDAO.categoryExists(this.cafe_brand, name)) {
            view.showError("Name is taken.", "Please provide a different category name.");
        } else {
            Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
            ProductCategory category = new ProductCategory(name, description, cafe);
            menuDAO.saveCategory(category);
            view.successfulNewCategory();
        }
    }
}
