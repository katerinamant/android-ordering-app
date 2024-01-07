package com.example.softcafeengineer.view.Order.ViewCategories;

import android.util.Log;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ViewCategoriesPresenter
{
    private ViewCategoriesView view;
    private MenuDAO menuDAO;
    private CafeteriaDAO cafeteriaDAO;
    private ActiveOrdersDAO activeOrdersDAO;
    private String cafe_brand;
    private ProductCategory category;
    private Cafeteria cafe;
    private Order order;
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

    public void setActiveOrdersDAO(ActiveOrdersDAO activeOrdersDAO) { this.activeOrdersDAO = activeOrdersDAO; }
    public ActiveOrdersDAO getActiveOrdersDAO()
    {
        return this.activeOrdersDAO;
    }

    public void setView(ViewCategoriesView view, String category_name, String cafe_brand, String unique_id) {
        this.view = view;
        this.cafe_brand = cafe_brand;
        this.category = menuDAO.findCategory(cafe_brand, category_name);
        this.order = activeOrdersDAO.find(unique_id);
        this.findAllAvailableProducts();
    }

    public String getCategoryName() { return this.category.getName(); }

    public String getCategoryDesc() { return this.category.getDescription(); }

    private void findAllAvailableProducts() {
        this.productResults.clear();
        List<Product> products = menuDAO.findAllProductsOfCategory(this.cafe_brand, this.category);
        for(Product p : products) {
            if(p.getAvailability()) {
                // Product is available
                this.productResults.add(p);
            }
        }
    }
    public List<Product> getProductResults() {
        return this.productResults;
    }

    public void onConfirmAddToCart(Product product, boolean confirm_enabled, String quantity_string, String comments) {
        if(!confirm_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else {
            int quantity = Integer.parseInt(quantity_string);
            if(quantity == 0) {
                view.showError("Invalid input.", "Please provide a valid quantity.");
            } else {
                // Update order object
                Log.d("ViewCategoriesPresenter", comments);
                OrderInfo orderInfo = new OrderInfo(quantity, product, comments);
                order.addToOrder(orderInfo);
                view.successfulAddToCart();
            }
        }
    }
}
