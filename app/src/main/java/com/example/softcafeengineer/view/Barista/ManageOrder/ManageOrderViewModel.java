package com.example.softcafeengineer.view.Barista.ManageOrder;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;

public class ManageOrderViewModel extends ViewModel
{
    private ManageOrderPresenter presenter;

    public ManageOrderViewModel() {
        this.presenter = new ManageOrderPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
    }

    public ManageOrderPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
