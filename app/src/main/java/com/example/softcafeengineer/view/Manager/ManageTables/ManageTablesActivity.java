package com.example.softcafeengineer.view.Manager.ManageTables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Table;

import java.util.List;

public class ManageTablesActivity extends AppCompatActivity implements ManageTablesView, TableRecyclerViewAdapter.ItemSelectionListener
{
    private ManageTablesViewModel viewModel;
    private String brand;
    private RelativeLayout relativeLayout;

    // Add new table pop up
    private PopupWindow add_table_popup;
    private EditText addTableNumberField, addUniqueIdField;
    private Button addTableButton;
    private boolean add_table_enabled;

    private Table selected_table;
    // Edit table pop up
    private PopupWindow edit_table_popup;
    private Integer prev_table_number;
    private String prev_unique_id;
    private EditText editTableNumberField, editUniqueIdField;
    private Button confirmEditButton;
    private boolean confirm_edit_enabled, text_changed;
    private String newTableNumber, newEditUniqueId;

    // Delete table pop up
    private PopupWindow delete_table_popup;
    private Button confirmDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tables);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
        }

        viewModel = new ViewModelProvider(this).get(ManageTablesViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setBrand(brand); // updates table results
        List<Table> tableList = viewModel.getPresenter().getTableResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_tables);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TableRecyclerViewAdapter(tableList, this));

        Button addNewButton = findViewById(R.id.btn_manage_tables_add); // "Add new" button
        relativeLayout = (RelativeLayout) findViewById(R.id.relative); // activity_manage_tables.xml layout
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_table, null);

                // Create and show add table popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                add_table_popup = new PopupWindow(pop_up, width, height, true);
                add_table_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                addTableNumberField = pop_up.findViewById(R.id.edit_text_add_table_number);
                addUniqueIdField = pop_up.findViewById(R.id.edit_text_add_table_id);
                addTableNumberField.addTextChangedListener(newTableWatcher);
                addUniqueIdField.addTextChangedListener(newTableWatcher);

                addTableButton = pop_up.findViewById(R.id.btn_final_add_table);
                addTableButton.setOnClickListener(onAddTableButton);
                // Add button is disabled
                add_table_enabled = false;
                addTableButton.setAlpha(.5f);

                Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_table);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    // User clicked the cancel button
                    // inside the add new table pop up
                    @Override
                    public void onClick(View v) {
                        add_table_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
                    }
                });
            }
        });
    }

    TextWatcher newTableWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in new table popup
            newTableNumber = addTableNumberField.getText().toString();
            newEditUniqueId = addUniqueIdField.getText().toString();
            if(!newTableNumber.isEmpty() && !newEditUniqueId.isEmpty()) {
                addTableButton.setAlpha(1.0f);
                add_table_enabled = true;
            } else {
                addTableButton.setAlpha(.5f);
                add_table_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener onAddTableButton = new View.OnClickListener() {
        // User clicked the add button
        // inside the add new table pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onAddNewTable(add_table_enabled, newTableNumber, newEditUniqueId);
        }
    };

    // -------
    // ManageTableView implementations
    // -------
    @Override
    public void successfulNewTable() {
        // User successfully added a new table
        // Restart activity with new tables list
        add_table_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulEdit() {
        // User successfully edited a table
        // Restart activity with an updated tables list
        edit_table_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulDelete() {
        // User successfully deleted a table
        // Restart activity with an updated tables list
        delete_table_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ManageTablesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void editTable(Table t) {
        selected_table = t;
        prev_table_number = t.getId();
        prev_unique_id = t.getQRCode();
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_edit_table, null);

        // Create and show edit table popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        edit_table_popup = new PopupWindow(pop_up, width, height, true);
        edit_table_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        editTableNumberField = pop_up.findViewById(R.id.edit_text_edit_table_number);
        editTableNumberField.setText(String.format("%d", prev_table_number));
        editUniqueIdField = pop_up.findViewById(R.id.edit_text_edit_table_id);
        editUniqueIdField.setText(prev_unique_id);
        editTableNumberField.addTextChangedListener(editTableWatcher);
        editUniqueIdField.addTextChangedListener(editTableWatcher);

        confirmEditButton = pop_up.findViewById(R.id.btn_final_edit_table);
        confirmEditButton.setOnClickListener(onConfirmEditButton);
        // Add button is disabled
        confirm_edit_enabled = true;
        confirmEditButton.setAlpha(1.0f);

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_edit_table);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the add new table pop up
            @Override
            public void onClick(View v) {
                edit_table_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    TextWatcher editTableWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Fields modified in edit table popup
            text_changed = true;
            newTableNumber = editTableNumberField.getText().toString();
            newEditUniqueId = editUniqueIdField.getText().toString().trim();
            if(!newTableNumber.isEmpty() && !newEditUniqueId.isEmpty()) {
                confirmEditButton.setAlpha(1.0f);
                confirm_edit_enabled = true;
            } else {
                confirmEditButton.setAlpha(.5f);
                confirm_edit_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener onConfirmEditButton = new View.OnClickListener() {
        // User clicked the confirm button
        // inside the edit table pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onEditTable(selected_table, confirm_edit_enabled, text_changed, prev_table_number, prev_unique_id, newTableNumber, newEditUniqueId);
        }
    };


    @Override
    public void deleteTable(Table t) {
        selected_table = t;
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_delete_table, null);

        // Create and show edit table popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        delete_table_popup = new PopupWindow(pop_up, width, height, true);
        delete_table_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        confirmDeleteButton = pop_up.findViewById(R.id.btn_final_delete_table);
        confirmDeleteButton.setOnClickListener(onConfirmDeleteButton);

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_delete_table);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the add new table pop up
            @Override
            public void onClick(View v) {
                delete_table_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    View.OnClickListener onConfirmDeleteButton = new View.OnClickListener() {
        // User clicked the confirm button
        // inside the delete table pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onDeleteTable(selected_table);
        }
    };
}
