package com.example.softcafeengineer.view.StartScreens;

public class WelcomeScreenPresenter {
    private final WelcomeScreenView view;

    public WelcomeScreenPresenter(WelcomeScreenView view) {
        this.view = view;
    }

    void onOrder() {
        view.startOrdering();
    }

    void onManagerLogIn() {
        view.managerLogIn();
    }

    void onEmployeeLogIn() {
        view.employeeLogIn();
    }

    void onSignUp() {
        view.signUp();
    }
}
