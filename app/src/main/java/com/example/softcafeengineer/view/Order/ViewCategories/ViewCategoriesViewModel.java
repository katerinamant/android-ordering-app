package com.example.softcafeengineer.view.Order.ViewCategories;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

public class ViewCategoriesViewModel extends ViewModel
{
    private ViewCategoriesPresenter presenter;

    public ViewCategoriesViewModel() {
        this.presenter = new ViewCategoriesPresenter();
        MenuDAO menuDAO = new MenuDAOMemory();
        presenter.setMenuDAO(menuDAO);
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        ActiveOrdersDAO activeOrdersDAO = new ActiveOrdersDAOMemory();
        presenter.setActiveOrdersDAO(activeOrdersDAO);
    }

    public ViewCategoriesPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();;
        // release rescources
    }
}
