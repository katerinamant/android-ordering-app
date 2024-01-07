package com.example.softcafeengineer.view.Order.ScanTable;

import com.example.softcafeengineer.domain.Status;

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
    public void showOrderStatus(Status orderStatus) {

    }

    @Override
    public void exitStatusPopup() {

    }

    @Override
    public void showCancelNotice() {

    }

    @Override
    public void exitCancelPopupOnYes() {

    }

    @Override
    public void exitCancelPopupOnNo() {

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
