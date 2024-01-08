package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.domain.Cafeteria;

public interface EditInfoView {
    /**
     * When the user clicks on the "Finish" button
     * with valid information
     * they are shown a confirm changes popup
     */
    void validFinish();

    /**
     * When the user clicks on the "Confirm" button
     * inside the confirm changes popup
     * they are redirected to the ManagerActionsActivity
     */
    void successfulFinish(Cafeteria cafe);

    void showToast(String msg);
}
