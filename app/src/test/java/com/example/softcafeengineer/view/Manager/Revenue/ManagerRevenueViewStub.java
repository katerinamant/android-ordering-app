package com.example.softcafeengineer.view.Manager.Revenue;

public class ManagerRevenueViewStub implements ManagerRevenueView {
    private int year, month, day;
    private String errorTitle, errorMessage, toastMessage;
    private ManagerRevenuePresenter presenter;

    public ManagerRevenueViewStub() {
        year = month = day = 0;
        errorTitle = errorMessage = toastMessage = "";
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public ManagerRevenuePresenter getPresenter() {
        return presenter;
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

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setPresenter(ManagerRevenuePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCalculation(double monthTotal, double dayTotal) {

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
