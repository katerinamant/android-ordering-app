package com.example.softcafeengineer.view.Manager.EditMenu;

public interface EditMenuView
{
    /**
     * User successfully
     * added new product category
     */
    void successfulNewCategory();

    void showToast(String s);

    void showError(String s, String s1);
}
