package com.example.softcafeengineer.view.Manager.EditMenu;

public class EditMenuViewStub implements EditMenuView{
    private String errorTitle, errorMessage, toastMessage;

    @Override
    public void successfulNewCategory() {

    }
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
    public void showToast(String s) {
        toastMessage = s;
    }

    @Override
    public void showError(String s, String s1) {
        errorTitle = s;
        errorMessage = s1;
    }
}
