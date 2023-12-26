package com.example.softcafeengineer.view.Manager.ManageTables;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

public class ManageTablesViewModel extends ViewModel {
    private ManageTablesPresenter presenter;

    public ManageTablesViewModel() {
        this.presenter = new ManageTablesPresenter();
        TableDAO tableDAO = new TableDAOMemory();
        presenter.setTableDAO(tableDAO);
    }

    public ManageTablesPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}