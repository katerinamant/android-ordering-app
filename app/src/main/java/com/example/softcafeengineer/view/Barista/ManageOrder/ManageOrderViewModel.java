package com.example.softcafeengineer.view.Barista.ManageOrder;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;

public class ManageOrderViewModel extends ViewModel
{
    private ManageOrderPresenter presenter;

    public ManageOrderViewModel() {
        this.presenter = new ManageOrderPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        BaristaDAO baristaDAO = new BaristaDAOMemory();
        presenter.setBaristaDAO(baristaDAO);
        RevenueDAO revenueDAO = new RevenueDAOMemory();
        presenter.setRevenueDAO(revenueDAO);
    }

    public ManageOrderPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
