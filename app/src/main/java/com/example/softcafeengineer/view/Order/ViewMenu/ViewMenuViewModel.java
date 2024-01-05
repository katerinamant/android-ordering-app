package com.example.softcafeengineer.view.Order.ViewMenu;

import androidx.lifecycle.ViewModel;

import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;


public class ViewMenuViewModel extends ViewModel
{
    private ViewMenuPresenter presenter;

    public ViewMenuViewModel() {
        this.presenter = new ViewMenuPresenter();
        MenuDAO menuDAO = new MenuDAOMemory();
        this.presenter.setMenuDAO(menuDAO);
        TableDAO tableDAO = new TableDAOMemory();
        this.presenter.setTableDAO(tableDAO);
    }

    public ViewMenuPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
