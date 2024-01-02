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
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;

import java.util.List;

public class ManageTablesActivity extends AppCompatActivity implements ManageTablesView, TableRecyclerViewAdapter.ItemSelectionListener
{
    private ManageTablesViewModel viewModel;
    private String brand;
    private RecyclerView recyclerView;
    private EditText tableNumberField, uniqueIdField;
    private Button addTableButton; // Add new button in pop-up
    private boolean add_table_enabled;
    private String tableNumber, uniqueId;

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
        recyclerView = findViewById(R.id.recycler_view_tables);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TableRecyclerViewAdapter(tableList, this));

        Button addNewButton = findViewById(R.id.btn_manage_tables_add); // "Add new" button
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative); // activity_manage_tables.xml layout
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_add_table, null);

                // Create and show popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                PopupWindow popupWindow = new PopupWindow(pop_up, width, height, true);
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                tableNumberField = pop_up.findViewById(R.id.edit_text_table_number);
                uniqueIdField = pop_up.findViewById(R.id.edit_text_table_id);
                tableNumberField.addTextChangedListener(newTableWatcher);
                uniqueIdField.addTextChangedListener(newTableWatcher);

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
                        popupWindow.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
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
            tableNumber = tableNumberField.getText().toString();
            uniqueId = uniqueIdField.getText().toString();
            if(!tableNumber.isEmpty() && !uniqueId.isEmpty()) {
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
            viewModel.getPresenter().onAddNewTable(add_table_enabled, tableNumber, uniqueId);
        }
    };

    @Override
    public void successfulNewTable() {
        // User successfully added a new table
        // Restart activity with new tables list
        finish();
        startActivity(getIntent());
    }

    @Override
    public void deleteTable(Table t) {
        // doesnt work, should add pop up
        viewModel.getPresenter().deleteTable(t);
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ManageTablesActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
