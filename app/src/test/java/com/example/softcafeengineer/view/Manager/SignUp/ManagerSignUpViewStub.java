package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.domain.User;

public class ManagerSignUpViewStub implements ManagerSignUpView {
    private String username;
    private String password;
    private final String errorTitle;
    private final String errorMessage;
    private String toastMessage;
    private ManagerSignUpPresenter presenter;

    public ManagerSignUpViewStub() {
        username = password = errorTitle = errorMessage = toastMessage = "";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public ManagerSignUpPresenter getPresenter() {
        return presenter;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPresenter(ManagerSignUpPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void successfulContinue(User newUser) {

    }

    @Override
    public void showToast(String msg) {
        toastMessage = msg;
    }
}
