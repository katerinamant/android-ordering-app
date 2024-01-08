package com.example.softcafeengineer.view.Manager.EditInfo;

import com.example.softcafeengineer.domain.Cafeteria;

public class EditInfoViewStub implements EditInfoView {
    private String toastMessage;

    public String getToastMessage() {
        return toastMessage;
    }

    @Override
    public void validFinish() {

    }

    @Override
    public void successfulFinish(Cafeteria cafe) {

    }

    @Override
    public void showToast(String msg) {
        this.toastMessage = msg;
    }
}
