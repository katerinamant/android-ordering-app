package com.example.softcafeengineer.view.Barista.Actions;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

import java.util.Calendar;

public class BaristaActionsViewModel extends ViewModel {
    private BaristaActionsPresenter presenter;

    public BaristaActionsViewModel() {
        this.presenter = new BaristaActionsPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        RevenueDAO revenueDAO = new RevenueDAOMemory();
        presenter.setRevenueDAO(revenueDAO);
        // Get current date
        Calendar calendar = Calendar.getInstance();
        presenter.setDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
    }

    public BaristaActionsPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
