package com.example.softcafeengineer.view.Manager.InfoInput;

public interface InfoInputView
{
    /**
     * When the user clicks on the "Finish" button
     * with valid credentials
     * they are redirected to the ManagerActionsActivity
     */
    void successfulFinish();

    void showToast(String msg);
}
