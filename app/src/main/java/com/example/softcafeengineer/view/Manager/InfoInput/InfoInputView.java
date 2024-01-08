package com.example.softcafeengineer.view.Manager.InfoInput;

import com.example.softcafeengineer.domain.Cafeteria;

public interface InfoInputView {
    /**
     * When the user clicks on the "Finish" button
     * with valid credentials
     * they are redirected to the ManagerActionsActivity
     */
    void successfulFinish(Cafeteria cafe);

    void showToast(String msg);
}
