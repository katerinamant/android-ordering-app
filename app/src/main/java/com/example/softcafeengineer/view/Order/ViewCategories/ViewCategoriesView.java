package com.example.softcafeengineer.view.Order.ViewCategories;

public interface ViewCategoriesView
{
    /**
     * User successfully
     * added new product
     * to cart
     */
    void successfulAddToCart();

    void showError(String title, String msg);

    void showToast(String msg);
}
