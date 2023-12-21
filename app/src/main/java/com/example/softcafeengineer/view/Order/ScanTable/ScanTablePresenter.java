package com.example.softcafeengineer.view.Order.ScanTable;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.domain.User;

public class ScanTablePresenter
{
    private ScanTableView  view;
    private TableDAO tables;

    public ScanTablePresenter(ScanTableView view, TableDAO tables) {
        this.view = view;
        this.tables = tables;
    }

    void onSubmit(boolean submit_button_enabled, String id) {
        if(!submit_button_enabled) {
            // Fields not filled, showing toast
            view.showToast("Please fill the required field.");
        }
        else {
            // Look up table
            Table result = tables.find(id);

            if(result != null) {
                // Correct id, change Activity
                view.successfulSubmit();
            } else {
                // Incorrect id, showing error
                view.showError("Connection unsuccessful.", "The id provided was invalid. Try again.");
            }
        }
    }
}
