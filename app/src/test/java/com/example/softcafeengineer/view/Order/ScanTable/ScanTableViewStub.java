package com.example.softcafeengineer.view.Order.ScanTable;

public class ScanTableViewStub implements ScanTableView{
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
    public void showOrderStatus() {

    }

    @Override
    public void successfulSubmit(String unique_id) {

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
