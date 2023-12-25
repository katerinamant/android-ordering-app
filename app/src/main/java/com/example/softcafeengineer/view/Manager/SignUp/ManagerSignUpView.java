package com.example.softcafeengineer.view.Manager.SignUp;

//
public interface ManagerSignUpView
{
    /**
    * When the user clicks on the "Continue" button
    * with valid credentials
    * they are redirected to the InfoInputActivity
    */
    void successfulContinue();

    void showToast(String msg);
}
