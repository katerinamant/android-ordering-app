package com.example.softcafeengineer.view.Manager.EditCategories;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class EditCategoriesPresenter
{
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private EditCategoriesView view;
    private String cafe_brand;
    private ProductCategory category;
    private Cafeteria cafe;
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

    public void setView(EditCategoriesView view, String category_name, String cafe_brand) {
        this.view = view;
        this.cafe_brand = cafe_brand;
        this.category = menuDAO.findCategory(cafe_brand, category_name);
        this.cafe = cafeteriaDAO.find(cafe_brand);
        this.findAllProducts(this.cafe_brand);
    }

    public void setBrand(String brand) {
        this.cafe_brand = brand;
        this.findAllProducts(this.cafe_brand);
    }
    public String getBrand()
    {
        return this.cafe_brand;
    }

    public String getCategoryName() { return this.category.getName(); }

    public String getCategoryDesc() { return this.category.getDescription(); }

    public void findAllProducts(String brand) {
        this.productResults.clear();
        this.productResults = menuDAO.findAllProducts(brand);
    }
    public List<Product> getProductResults() {
        return this.productResults;
    }

    public void onEditCategory(boolean confirm_edit_enabled, boolean text_changed, String prev_name, String prev_desc, String name, String desc) {
        if(!confirm_edit_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if(!text_changed) {
            // Fields filled and no text changed
            view.successfulEditCategory();
        } else {
            // Fields filled and text changed
            if(!name.equals(prev_name) && menuDAO.categoryExists(this.cafe_brand, name)) {
                // Name is in use, showing error
                view.showError("Name is taken.", "Please provide a different category name.");
            } else {
                // Update product category object
                if(!name.equals(prev_name)) this.category.setName(name);
                if(!desc.equals(prev_desc)) this.category.setDescription(desc);

                view.successfulEditCategory();
            }
        }
    }

    public void onDeleteCategory() {
        menuDAO.deleteCategory(this.category);
        view.successfulDeleteCategory();
    }

    public void onAddProduct(boolean addProductEnabled, String name, String price_string, boolean availability) {
        if (!addProductEnabled){
            view.showToast("Please fill all the required fields.");
        } else {
            if (this.menuDAO.productExists(this.cafe_brand, name)) {
                view.showError("Name is taken.", "Please provide a different product name.");
            } else {
                double price = Double.parseDouble(price_string);
                Product product = new Product(price, name, availability, this.category, this.cafe);
                menuDAO.saveProduct(product);
                view.successfulNewProduct();
            }
        }
    }

    public void onEditProduct(Product product, boolean confirm_edit_enabled, boolean text_changed, String prev_name, double prev_price, boolean prev_availability, String name, String price_string, boolean availability) {
        if(!confirm_edit_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if(!text_changed) {
            // Fields filled and no text changed
            view.successfulEditProduct();
        } else {
            // Fields filled and text changed
            if(!name.equals(prev_name) && menuDAO.productExists(this.cafe_brand, name)) {
                // Name is in use, showing error
                view.showError("Name is taken.", "Please provide a different product name.");
            } else {
                // Update product object
                double price = Double.parseDouble(price_string);
                if(!name.equals(prev_name)) product.setName(name);
                if(price != prev_price) product.setPrice(price);
                if(prev_availability != availability) product.setAvailability(availability);

                view.successfulEditProduct();
            }
        }
    }

    public void onDeleteProduct(Product product) {
        menuDAO.deleteProduct(product);
        view.successfulDeleteProduct();
    }
}
