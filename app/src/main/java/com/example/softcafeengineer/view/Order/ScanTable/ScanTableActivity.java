package com.example.softcafeengineer.view.Order.ScanTable;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Status;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;
import com.example.softcafeengineer.view.Order.ViewMenu.ViewMenuActivity;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

public class ScanTableActivity extends AppCompatActivity implements ScanTableView
{
    private ScanTablePresenter presenter;
    private RelativeLayout relativeLayout;
    private EditText idField;
    private Button submitButton;
    private boolean submit_button_enabled;
    private String unique_id;

    // Show status popup
    PopupWindow show_status_popup;

    // Order cancelled popup
    PopupWindow order_cancelled_popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_table);

        presenter = new ScanTablePresenter(this, new TableDAOMemory(), new ActiveOrdersDAOMemory(), new ActiveCartsDAOMemory());

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_scan_table); // activity_scan_table.xml layout

        idField = findViewById(R.id.edit_txt_table_id);
        submitButton = findViewById(R.id.btn_submit_table_id);

        // Submit button is disabled
        submit_button_enabled = false;
        submitButton.setAlpha(.5f); // set opacity to seem disabled
        idField.addTextChangedListener(idWatcher);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.onSubmit(submit_button_enabled, unique_id);
                } catch (InvalidDateException e) {
                    // Invalid date
                    throw new RuntimeException(e);
                }
            }
        });
    }

    TextWatcher idWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            unique_id = idField.getText().toString();
            if(!unique_id.isEmpty()) {
                submitButton.setAlpha(1.0f);
                submit_button_enabled = true;
            } else {
                submitButton.setAlpha(.5f);
                submit_button_enabled = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void showOrderStatus(Status orderStatus) {
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_order_status, null);

        // Create and show delete table popup
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        show_status_popup = new PopupWindow(pop_up, width, height, true);
        show_status_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        // Fill the order's status' TextView
        TextView statusText = pop_up.findViewById(R.id.txt_order_status);
        statusText.setText(orderStatus.toString());

        Button okButton = pop_up.findViewById(R.id.btn_ok_order_status);
        okButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the ok button
            // inside the show status pop up
            @Override
            public void onClick(View v) { presenter.onOkStatus(); }
        });
    }

    @Override
    public void exitStatusPopup() {
        show_status_popup.dismiss();
        Intent intent = new Intent(ScanTableActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCancelNotice() {
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_order_cancelled, null);

        // Create and show delete table popup
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        order_cancelled_popup = new PopupWindow(pop_up, width, height, true);
        order_cancelled_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        Button yesButton = pop_up.findViewById(R.id.btn_yes_new_order);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onYesOrder(unique_id); }
        });

        Button noButton = pop_up.findViewById(R.id.btn_no_new_order);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenter.onNoOrder(unique_id); }
        });
    }

    @Override
    public void exitCancelPopupOnYes() {
        order_cancelled_popup.dismiss();
        ScanTableActivity.this.successfulSubmit(unique_id);
    }

    @Override
    public void exitCancelPopupOnNo() {
        order_cancelled_popup.dismiss();
        Intent intent = new Intent(ScanTableActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void successfulSubmit(String unique_id) {
        Intent intent = new Intent(ScanTableActivity.this, ViewMenuActivity.class);
        intent.putExtra("unique_id", unique_id);
        startActivity(intent);
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ScanTableActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
