package com.example.softcafeengineer.view.Order.ViewCategories;

public class ViewCategoriesViewStub implements ViewCategoriesView{
    private String errorTitle, errorMessage, toastMessage;
    public String getToastMessage(){
        return toastMessage;
    }
    public String getErrorTitle(){
        return errorTitle;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    @Override
    public void successfulAddToCart() {

    }

    @Override
    public void showError(String title, String msg) {
        errorMessage = msg;
        errorTitle = title;
    }

    @Override
    public void showToast(String msg) {
        toastMessage = msg;
    }
}
