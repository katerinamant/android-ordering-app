package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.domain.Cafeteria;

public class InfoInputViewStub implements InfoInputView {
    private String toastMessage, address, brand, username, password, phoneNumber, ssn;
    private InfoInputPresenter presenter;

    public InfoInputViewStub() {
        toastMessage = address = brand = username = password = phoneNumber = ssn = "";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public String getSSN() {
        return ssn;
    }

    public void setPresenter(InfoInputPresenter presenter) {
        this.presenter = presenter;
    }

    public InfoInputPresenter getPresenter() {
        return presenter;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    @Override
    public void successfulFinish(Cafeteria cafe) {

    }

    @Override
    public void showToast(String msg) {
        this.toastMessage = msg;
    }
}
