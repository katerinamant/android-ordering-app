package com.example.softcafeengineer.view.Order.ViewCart;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;

public class ViewCartViewModel extends ViewModel {
    private final ViewCartPresenter presenter;

    public ViewCartViewModel() {
        this.presenter = new ViewCartPresenter();
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
        ActiveCartsDAO activeCartsDAO = new ActiveCartsDAOMemory();
        presenter.setActiveCartsDAO(activeCartsDAO);
    }

    public ViewCartPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
