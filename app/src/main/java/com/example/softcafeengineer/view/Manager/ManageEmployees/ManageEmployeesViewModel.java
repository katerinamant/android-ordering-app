package com.example.softcafeengineer.view.Manager.ManageEmployees;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;

public class ManageEmployeesViewModel extends ViewModel {
    private final ManageEmployeesPresenter presenter;

    public ManageEmployeesViewModel() {
        this.presenter = new ManageEmployeesPresenter();
        BaristaDAO baristaDAO = new BaristaDAOMemory();
        presenter.setBaristaDAO(baristaDAO);
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        presenter.setCafeteriaDAO(cafeteriaDAO);
    }

    public ManageEmployeesPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
