package com.example.softcafeengineer.view.Barista.ManageOrder;

public class ManageOrderViewStub implements ManageOrderView {
    private String errorTitle, errorMessage, toastMessage;

    public String getToastMessage() {
        return toastMessage;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void successfulExecution() {

    }

    @Override
    public void successfulCompletion() {

    }

    @Override
    public void successfulCancellation() {

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
