package com.example.softcafeengineer.view.Barista.LogIn;

import com.example.softcafeengineer.domain.Barista;

public class BaristaLogInViewStub implements BaristaLogInView {
    private String errorTitle, errorMessage, toastMessage;

    public BaristaLogInViewStub() {
        errorMessage = errorTitle = toastMessage = "";
    }

    @Override
    public void successfulLogIn(Barista barista) {

    }

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
    public void showError(String title, String msg) {
        errorTitle = title;
        errorMessage = msg;
    }

    @Override
    public void showToast(String msg) {
        toastMessage = msg;
    }
}
