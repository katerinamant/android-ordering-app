package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.domain.Cafeteria;

public interface EditInfoView
{
    /**
     * When the user clicks on the "Finish" button
     * with valid info
     * they are redirected to the ManagerActionsActivity
     */
    void successfulFinish(Cafeteria cafe);

    void showToast(String s);
}
