package com.example.softcafeengineer.view.Manager.Actions;

public interface ManagerActionsView {

    /*
     * when the user clicks on the "Edit cafeteria's info" button
     * they are redirected to the ManagerEditCInfoActivity
     */
    void edit_Cinfo();

    /*
    * when the user clicks on the "Manage employees" button
    * they are redirected to the ManagerMEmployeesActivity
    */
    void manage_employees();

    /*
     * when the user clicks on the "Manage tables" button
     * they are redirected to the ManagerMTablesActivity
     */
    void manage_tables();

    /*
     * when the user clicks on the "Edit menu" button
     * they are redirected to the ManagerEditMenuActivity
     */
    void edit_menu();

    /*
     * when the user clicks on the "Revenue breakdown" button
     * they are redirected to the ManagerRevenueActivity
     */
    void revenue_breakdown();
}
