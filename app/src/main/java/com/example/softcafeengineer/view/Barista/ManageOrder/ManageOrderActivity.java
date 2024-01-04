package com.example.softcafeengineer.view.Barista.ManageOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public void showError(String title, String msg) {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(msg).setPositiveButton(R.string.ok, null).create().show();
    }


    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void manageOrderInfo(OrderInfo o) {
    }
}
