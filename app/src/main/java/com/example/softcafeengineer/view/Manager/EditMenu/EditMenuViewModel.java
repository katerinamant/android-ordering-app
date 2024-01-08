package com.example.softcafeengineer.view.Manager.EditMenu;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;

public class EditMenuViewModel extends ViewModel {
    private final EditMenuPresenter presenter;

    public EditMenuViewModel() {
        this.presenter = new EditMenuPresenter();
        MenuDAO menuDAO = new MenuDAOMemory();
        presenter.setMenuDAO(menuDAO);
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        presenter.setCafeteriaDAO(cafeteriaDAO);
    }

    public EditMenuPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
