package com.example.softcafeengineer.view.Manager.EditCategories;

public class EditCategoriesViewStub implements EditCategoriesView {
    private String errorTitle, errorMessage, toastMessage;
    private EditCategoriesPresenter presenter;

    public EditCategoriesPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(EditCategoriesPresenter presenter) {
        this.presenter = presenter;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    @Override
    public void successfulEditCategory() {

    }

    @Override
    public void successfulDeleteCategory() {

    }

    @Override
    public void successfulNewProduct() {

    }

    @Override
    public void successfulEditProduct() {

    }

    @Override
    public void successfulDeleteProduct() {

    }

    @Override
    public void showError(String title, String msg) {
        errorTitle = title;
        errorMessage = msg;
    }

    @Override
    public void showToast(String msg) {
        toastMessage = msg;
    }
}
