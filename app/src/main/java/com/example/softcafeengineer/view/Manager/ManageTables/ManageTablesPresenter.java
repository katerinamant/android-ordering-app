package com.example.softcafeengineer.view.Manager.ManageTables;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.List;

public class ManageTablesPresenter
{
    private TableDAO tableDAO;
    private ManageTablesView view;
    private List<Table> tableResults = new ArrayList<>();

    public void setTableDAO(TableDAO tableDAO) { this.tableDAO = tableDAO; }
    public TableDAO getTableDAO() { return this.tableDAO; }

    public void setView(ManageTablesView view) { this.view = view; }

    public void findAll(String brand) {
        tableResults.clear();
        tableResults = tableDAO.findAll(brand);
    }

    public List<Table> getTableResults() {
        return this.tableResults;
    }

    public void deleteTable(Table t) {
        tableDAO.delete(t);
    }
}
