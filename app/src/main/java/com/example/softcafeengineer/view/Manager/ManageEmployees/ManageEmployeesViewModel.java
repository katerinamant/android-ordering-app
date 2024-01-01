package com.example.softcafeengineer.view.Manager.ManageEmployees;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;

public class ManageEmployeesViewModel extends ViewModel {
    ManageEmployeesPresenter presenter;
    public ManageEmployeesViewModel(){
        presenter = new ManageEmployeesPresenter(new BaristaDAOMemory(), new ManagerDAOMemory());
    }
    public ManageEmployeesPresenter getPresenter(){
        return presenter;
    }
}
