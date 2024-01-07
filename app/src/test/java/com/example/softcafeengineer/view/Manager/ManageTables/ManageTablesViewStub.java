package com.example.softcafeengineer.view.Manager.ManageTables;

import com.example.softcafeengineer.view.Manager.ManageEmployees.ManageEmployeesPresenter;

public class ManageTablesViewStub implements ManageTablesView
{
    private String QRCode, tableID, errorTitle, errorMessage, toastMessage;

    private ManageTablesPresenter presenter;
    public ManageTablesViewStub(){
        QRCode = tableID = errorMessage = errorTitle = toastMessage = "";
    }
    public String getQRCode(){
        return QRCode;
    }
    public String getTableID(){
        return tableID;
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
    public ManageTablesPresenter getPresenter(){
        return presenter;
    }
    public void setQRCode(String QRCode){
        this.QRCode = QRCode;
    }
    public void setTableID(String tableID){
        this.tableID = tableID;
    }
    public void setPresenter(ManageTablesPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void successfulNewTable() {

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
