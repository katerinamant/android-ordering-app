package com.example.softcafeengineer.view.Manager.EditCategories;

public interface EditCategoriesView {
    /**
     * Dismiss popup window
     * when category is edited successfully
     */
    void successfulEditCategory();

    /**
     * Dismiss popup window
     * when category is deleted
     * and return to EditMenuActivity
     */
    void successfulDeleteCategory();

    /**
     * User successfully
     * added new product
     */
    void successfulNewProduct();

    /**
     * Dismiss popup window
     * when product is edited successfully
     */
    void successfulEditProduct();


    void successfulDeleteProduct();

    void showError(String title, String msg);

    void showToast(String msg);
}
