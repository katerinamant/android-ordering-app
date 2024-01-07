package com.example.softcafeengineer.view.Order.ViewCart;


public class ViewCartViewStub implements ViewCartView{
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
    public void onSuccessfulSubmitOrder() {

    }

    @Override
    public void successfulEdit() {

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
