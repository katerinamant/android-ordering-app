package com.example.softcafeengineer.view.Manager.Actions;

public class ManagerActionsPresenter {
    private final ManagerActionsView view;

    public ManagerActionsPresenter(ManagerActionsView view) {
        this.view = view;
    }

    void onEditCInfo() {
        view.edit_Cinfo();
    }

    void onManageEmployees() {
        view.manage_employees();
    }

    void onManageTables() {
        view.manage_tables();
    }

    void onEditMenu() {
        view.edit_menu();
    }

    void onRevenueBreakdown() {
        view.revenue_breakdown();
    }

    public void onLogOutButton() {
        view.log_out();
    }
}
