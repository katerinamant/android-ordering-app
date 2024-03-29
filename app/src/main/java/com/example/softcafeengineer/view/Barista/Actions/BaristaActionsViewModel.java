package com.example.softcafeengineer.view.Barista.Actions;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

public class BaristaActionsViewModel extends ViewModel {
    private final BaristaActionsPresenter presenter;

    public BaristaActionsViewModel() {
        this.presenter = new BaristaActionsPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        RevenueDAO revenueDAO = new RevenueDAOMemory();
        presenter.setRevenueDAO(revenueDAO);
    }

    public BaristaActionsPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
