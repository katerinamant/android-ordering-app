package com.example.softcafeengineer.view.Order.ViewMenu;


import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.List;

public class ViewMenuPresenter
{
    private ViewMenuView view;
    private MenuDAO menuDAO;
    private TableDAO tableDAO;
    private Table table;
    private List<ProductCategory> categoryResults = new ArrayList<ProductCategory>();

    public void setMenuDAO(MenuDAO menuDAO)
    {
        this.menuDAO = menuDAO;
    }
    public MenuDAO getMenuDAO()
    {
        return this.menuDAO;
    }

    public void setTableDAO(TableDAO tableDAO)
    {
        this.tableDAO = tableDAO;
    }
    public TableDAO getTableDAO()
    {
        return this.tableDAO;
    }

    public void setView(ViewMenuView view, String unique_id) {
        this.view = view;
        this.table = this.tableDAO.find(unique_id);
        this.findAllCategories(this.table.getCafe().getBrand());
    }

    public void findAllCategories(String brand) {
        this.categoryResults.clear();
        this.categoryResults = menuDAO.findAllCategories(brand);
    }
    public List<ProductCategory> getCategoryResults() {
        return this.categoryResults;
    }

    public void onViewCart() {
        view.onViewingCart();
    }
}
