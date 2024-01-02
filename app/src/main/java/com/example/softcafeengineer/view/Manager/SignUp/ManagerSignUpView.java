package com.example.softcafeengineer.view.Manager.SignUp;

import com.example.softcafeengineer.domain.User;


public interface ManagerSignUpView
{
    /**
    * When the user clicks on the "Continue" button
    * with valid credentials
    * they are redirected to the InfoInputActivity
    */
    void successfulContinue(User newUser);

    void showToast(String msg);
}
