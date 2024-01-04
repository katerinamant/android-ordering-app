package com.example.softcafeengineer.view.Manager.EditMenu;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.dao.MenuDAO;

public class EditMenuViewModel extends ViewModel
{
    private EditMenuPresenter presenter;

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
}
