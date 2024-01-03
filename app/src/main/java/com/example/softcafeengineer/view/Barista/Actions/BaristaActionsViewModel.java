package com.example.softcafeengineer.view.Barista.Actions;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

public class BaristaActionsViewModel extends ViewModel {
    private BaristaActionsPresenter presenter;

    public BaristaActionsViewModel() {
        this.presenter = new BaristaActionsPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        MonthlyRevenueDAO monthlyRevenueDAO = new MonthlyRevenueDAOMemory();
        presenter.setMonthlyRevenueDAO(monthlyRevenueDAO);
        /*
         TODO
          presenter.setDate(1, 1, 1);
        */
    }

    public BaristaActionsPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
