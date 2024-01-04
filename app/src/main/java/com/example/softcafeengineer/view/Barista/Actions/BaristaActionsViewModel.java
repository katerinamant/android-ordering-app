package com.example.softcafeengineer.view.Barista.Actions;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.MonthlyRevenueDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.MonthlyRevenueDAOMemory;

import java.util.Calendar;

public class BaristaActionsViewModel extends ViewModel {
    private BaristaActionsPresenter presenter;

    public BaristaActionsViewModel() {
        this.presenter = new BaristaActionsPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        MonthlyRevenueDAO monthlyRevenueDAO = new MonthlyRevenueDAOMemory();
        presenter.setMonthlyRevenueDAO(monthlyRevenueDAO);
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
