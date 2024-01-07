package com.example.softcafeengineer.view.Manager.LogIn;

import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.view.Manager.ManageEmployees.ManageEmployeesPresenter;
import com.example.softcafeengineer.view.Manager.ManageEmployees.ManageEmployeesViewStub;

public class ManagerLogInViewStub implements ManagerLogInView
{
    private String username, password, errorTitle, errorMessage, toastMessage;
    private ManagerLogInPresenter presenter;

    public ManagerLogInViewStub(){
        username = password = errorTitle = errorMessage = toastMessage = "";
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getErrorTitle(){
        return errorTitle;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public String getToastMessage(){
        return toastMessage;
    }
    public ManagerLogInPresenter getPresenter(){
        return presenter;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPresenter(ManagerLogInPresenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void successfulLogIn(Cafeteria cafe) {

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
