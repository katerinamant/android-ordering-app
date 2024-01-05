package com.example.softcafeengineer.view.Manager.EditCategories;

public interface EditCategoriesView {
    /**
     * User successfully
     * added new product
     */
    void successfulNewProduct();

    void showToast(String s);

    void showError(String s, String s1);
}
