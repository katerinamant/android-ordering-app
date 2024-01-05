package com.example.softcafeengineer.view.Manager.EditCategories;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.view.Manager.EditMenu.EditMenuPresenter;

public class EditCategoriesViewModel extends ViewModel
{
    private EditCategoriesPresenter presenter;

    public EditCategoriesViewModel() {
        this.presenter = new EditCategoriesPresenter();
        MenuDAO menuDAO = new MenuDAOMemory();
        presenter.setMenuDAO(menuDAO);
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        presenter.setCafeteriaDAO(cafeteriaDAO);
    }

    public EditCategoriesPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
