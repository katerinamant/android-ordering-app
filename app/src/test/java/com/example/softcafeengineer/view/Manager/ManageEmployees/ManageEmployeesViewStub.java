package com.example.softcafeengineer.view.Manager.ManageEmployees;

public class ManageEmployeesViewStub implements ManageEmployeesView {
    private String username, password, errorTitle, errorMessage, toastMessage;
    private ManageEmployeesPresenter presenter;

    public ManageEmployeesViewStub() {
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

    public ManageEmployeesPresenter getPresenter() {
        return presenter;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPresenter(ManageEmployeesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void successfulNewEmployee() {

    }

    @Override
    public void successfulEdit() {

    }

    @Override
    public void successfulDelete() {

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
