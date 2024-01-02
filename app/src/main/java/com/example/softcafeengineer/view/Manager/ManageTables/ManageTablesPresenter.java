package com.example.softcafeengineer.view.Manager.ManageTables;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.List;

public class ManageTablesPresenter
{
    private TableDAO tableDAO;
    private CafeteriaDAO cafeteriaDAO;
    private ManageTablesView view;
    private String cafe_brand;
    private List<Table> tableResults = new ArrayList<>();

    public void setTableDAO(TableDAO tableDAO) { this.tableDAO = tableDAO; }
    public TableDAO getTableDAO() { return this.tableDAO; }

    public void setCafeteriaDAO(CafeteriaDAO cafeteriaDAO) { this.cafeteriaDAO = cafeteriaDAO; }
    public CafeteriaDAO getCafeteriaDAO() { return this.cafeteriaDAO; }

    public void setBrand(String brand) {
        // Set cafe brand
        // and update tableResults
        this.cafe_brand = brand;
        this.findAll(this.cafe_brand);
    }
    public String getBrand() { return this.cafe_brand; }

    public void setView(ManageTablesView view) { this.view = view; }

    public void findAll(String brand) {
        this.tableResults.clear();
        this.tableResults = tableDAO.findAll(brand);
    }

    public List<Table> getTableResults() {
        return this.tableResults;
    }

    public void onAddNewTable(boolean add_table_enabled, String tableNumber, String uniqueId) {
        if(!add_table_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        }
        else {
            if(this.tableDAO.exists(uniqueId)) {
                // Unique id is in use, showing error
                view.showError("Unique id is taken.", "Please provide a different unique id.");
            } else {
                // Add new table object
                Cafeteria cafe = this.cafeteriaDAO.find(this.cafe_brand);
                Table table = new Table(uniqueId, Integer.parseInt(tableNumber), cafe);
                tableDAO.save(table);
                view.successfulNewTable();
            }
        }
    }

    public void deleteTable(Table t) {
        tableDAO.delete(t);
        view.successfulDelete();
    }

    public void onEditTable(Table table, boolean confirm_edit_enabled, boolean text_changed, int prev_table_number, String prev_unique_id, String tableNumber_string, String uniqueId) {
        if(!confirm_edit_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill all the required fields.");
        } else if(!text_changed) {
            // Fields filled and no text changed
            view.successfulEdit();
        } else {
            // Fields filled and text changed
            if(!uniqueId.equals(prev_unique_id) && tableDAO.exists(uniqueId)) {
                // Unique id is in use, showing error
                view.showError("Unique id is taken.", "Please provide a different unique id.");
            } else {
                // Update table object
                int tableNumber = Integer.parseInt(tableNumber_string);
                if(tableNumber != prev_table_number) table.setId(tableNumber);
                if(!uniqueId.equals(prev_unique_id)) {
                    tableDAO.updateTable(prev_unique_id, uniqueId);
                }

                view.successfulEdit();
            }
        }
    }
}
