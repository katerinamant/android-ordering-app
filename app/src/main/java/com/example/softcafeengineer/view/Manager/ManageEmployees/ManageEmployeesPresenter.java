package com.example.softcafeengineer.view.Manager.ManageEmployees;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.domain.Barista;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeesPresenter {
    private BaristaDAO baristaDAO;
    private ManagerDAO managerDao;
    private List<Barista> employeeResults = new ArrayList<>();
    ManageEmployeesView view;
    public ManageEmployeesPresenter(BaristaDAO baristaDAO, ManagerDAO managerDAO){
        this.baristaDAO = baristaDAO;
        this.managerDao = managerDAO;
    }
    public void setView(ManageEmployeesView v){
        this.view = v;
    }
    public List<Barista> getEmployeeResults(){
        return this.employeeResults;
    }
    public void deleteEmloyee( Barista b ){
        baristaDAO.delete(b);
    }
}
