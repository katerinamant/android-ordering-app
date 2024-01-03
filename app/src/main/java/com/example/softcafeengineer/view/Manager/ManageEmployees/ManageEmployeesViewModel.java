package com.example.softcafeengineer.view.Manager.ManageEmployees;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;

public class ManageEmployeesViewModel extends ViewModel {
    private ManageEmployeesPresenter presenter;

    public ManageEmployeesViewModel() {
        this.presenter = new ManageEmployeesPresenter();
        BaristaDAO baristaDAO = new BaristaDAOMemory();
        presenter.setBaristaDAO(baristaDAO);
    }

    public ManageEmployeesPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
