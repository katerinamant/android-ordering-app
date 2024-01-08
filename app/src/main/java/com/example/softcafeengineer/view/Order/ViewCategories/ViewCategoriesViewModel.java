package com.example.softcafeengineer.view.Order.ViewCategories;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

public class ViewCategoriesViewModel extends ViewModel {
    private final ViewCategoriesPresenter presenter;

    public ViewCategoriesViewModel() {
        this.presenter = new ViewCategoriesPresenter();
        MenuDAO menuDAO = new MenuDAOMemory();
        presenter.setMenuDAO(menuDAO);
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        ActiveCartsDAO activeCartsDAO = new ActiveCartsDAOMemory();
        presenter.setActiveCartsDAO(activeCartsDAO);
    }

    public ViewCategoriesPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release rescources
    }
}
