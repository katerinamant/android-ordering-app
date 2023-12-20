package com.example.softcafeengineer.view.StartScreens;

public interface WelcomeScreenView
{
    /**
     * When the user clicks on "Order" button
     * they are redirected to the
     * ScanTableActivity
     */
    void startOrdering();

    /**
     * When the user clicks on
     * "Manager Log-In" button,
     * they are redirected to the
     * ManagerLogInActivity
     */
    void managerLogIn();

    /**
     * When the user clicks on
     * "Employee Log-In" button,
     * they are redirected to the
     * EmployeeLogInActivity
     */
    void employeeLogIn();

    /**
     * When the user clicks on
     * "Sign-up here" button,
     * they are redirected to the
     * SignUpActivity
     */
    void signUp();
}
