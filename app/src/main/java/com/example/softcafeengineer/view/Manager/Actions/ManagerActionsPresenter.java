package com.example.softcafeengineer.view.Manager.Actions;

import com.example.softcafeengineer.dao.ManagerDAO;

public class ManagerActionsPresenter
{
    private ManagerActionsView view;
//    private ManagerDAO users;
    public ManagerActionsPresenter(ManagerActionsView view){
        this.view = view;
//        this.users = users;
    }

    void onEditCInfo(){
        view.edit_Cinfo();
    }

    void onManageEmployees(){
        view.manage_employees();
    }

    void onManageTables(){
        view.manage_tables();
    }

    void onEditMenu(){
        view.edit_menu();
    }

    void onRevenueBreakdown(){
        view.revenue_breakdown();
    }
}
