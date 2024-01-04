package com.example.softcafeengineer.view.Barista.ManageOrder;

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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.OrderInfo;
import com.example.softcafeengineer.view.Barista.Actions.BaristaActionsActivity;

import java.util.List;

public class ManageOrderActivity extends AppCompatActivity implements ManageOrderView, OrderListRecyclerViewAdapter.ItemSelectionListener
{
    private ManageOrderViewModel viewModel;
    private String username, password, brand;
    private int table_number;
    private RelativeLayout relativeLayout;

    // Change status pop up
    private PopupWindow change_status_popup;

    // Edit order info pop up
    private OrderInfo selected_order_info;
    private PopupWindow edit_order_info_popup;
    private int prev_quantity;
    private EditText editQuantityField;
    private Button confirmEditButton;
    private boolean confirm_edit_enabled, text_changed;
    private String newQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
            password = intent.getStringExtra("password");
            brand = intent.getStringExtra("cafe_brand");
            table_number = intent.getIntExtra("table_number", -1);
        }

        viewModel = new ViewModelProvider(this).get(ManageOrderViewModel.class);
        viewModel.getPresenter().setView(this, username, password, brand, table_number);

        List<OrderInfo> orderList = viewModel.getPresenter().getOrderResults();

        TextView total_cost = findViewById(R.id.manage_order_total_cost);
        total_cost.setText(String.format("%.2f 💶", viewModel.getPresenter().getTotalCost()));
        TextView order_status = findViewById(R.id.manage_order_status);
        order_status.setText(String.format("%s", viewModel.getPresenter().getOrderStatus()));

        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderListRecyclerViewAdapter(orderList, this));

        Button changeStatusButton = findViewById(R.id.btn_manage_order_status); // "Change status" button
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_manage_order); // activity_manage_order.xml layout
        changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate popup layout
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View pop_up = layoutInflater.inflate(R.layout.popup_change_order_status, null);

                // Create and show add table popup
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                change_status_popup = new PopupWindow(pop_up, width, height, true);
                change_status_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

                Button waitingButton = pop_up.findViewById(R.id.btn_change_status_to_waiting);
                waitingButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onWaitingStatus();
                    }
                });
                Button inProgressButton = pop_up.findViewById(R.id.btn_change_status_to_in_progress);
                inProgressButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onInProgressStatus();
                    }
                });
                Button completedButton = pop_up.findViewById(R.id.btn_change_status_to_completed);
                completedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onCompletedStatus();
                    }
                });
                Button canceledButton = pop_up.findViewById(R.id.btn_change_status_to_canceled);
                canceledButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.getPresenter().onCanceledStatus();
                    }
                });
            }
        });
    }

    // -------
    // ManageOrderView implementations
    // -------
    @Override
    public void successfulExecution() {
        // Barista is executing the order
        change_status_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void successfulCompletion() {
        // Barista is redirected to the BaristaActionsActivity
        change_status_popup.dismiss();
        Intent intent = new Intent(ManageOrderActivity.this, BaristaActionsActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void successfulCancellation() {
        // Barista is redirected to the BaristaActionsActivity
        change_status_popup.dismiss();
        Intent intent = new Intent(ManageOrderActivity.this, BaristaActionsActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("cafe_brand", brand);
        startActivity(intent);
    }

    @Override
    public void successfulEdit() {
        edit_order_info_popup.dismiss();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ManageOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void editOrderInfo(OrderInfo orderInfo) {
        selected_order_info = orderInfo;
        prev_quantity = orderInfo.getQuantity();
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_edit_order_info, null);

        // Create and show edit table popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        edit_order_info_popup = new PopupWindow(pop_up, width, height, true);
        edit_order_info_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        editQuantityField = pop_up.findViewById(R.id.edit_text_order_info_quantity);
        editQuantityField.setText(String.format("%d", prev_quantity));
        editQuantityField.addTextChangedListener(editOrderInfoWatcher);

        confirmEditButton = pop_up.findViewById(R.id.btn_final_edit_order_info);
        confirmEditButton.setOnClickListener(onConfirmEditButton);
        // Confirm button is enabled
        confirm_edit_enabled = true;
        confirmEditButton.setAlpha(1.0f);
        text_changed = false;

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_edit_order_info);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            // User clicked the cancel button
            // inside the edit order info pop up
            @Override
            public void onClick(View v) {
                edit_order_info_popup.dismiss(); // this OnClickListener is declared here so the popup window can be dismissed
            }
        });
    }

    TextWatcher editOrderInfoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Field modified in edit order info popup
            text_changed = true;
            newQuantity = editQuantityField.getText().toString();
            if(!newQuantity.isEmpty()) {
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
        // inside the edit order info pop up
        @Override
        public void onClick(View v) {
            viewModel.getPresenter().onEditProductInfo(selected_order_info, confirm_edit_enabled, text_changed, prev_quantity, newQuantity);
        }
    };
}
