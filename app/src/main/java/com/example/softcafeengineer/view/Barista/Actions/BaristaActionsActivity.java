package com.example.softcafeengineer.view.Barista.Actions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Order;
import com.example.softcafeengineer.view.Barista.ManageOrder.ManageOrderActivity;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

import java.util.List;

public class BaristaActionsActivity extends AppCompatActivity implements BaristaActionsView, ActiveOrdersRecyclerViewAdapter.ItemSelectionListener
{
    private BaristaActionsViewModel viewModel;
    private String username, password, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barista_actions);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
            password = intent.getStringExtra("password");
            brand = intent.getStringExtra("cafe_brand");
        }
        TextView header = findViewById(R.id.txt_barista_actions_header);
        header.setText(String.format("Hello, %s!", username));

        viewModel = new ViewModelProvider(this).get(BaristaActionsViewModel.class);
        viewModel.getPresenter().setView(this);
        viewModel.getPresenter().setBrand(brand);

        List<Order> orderList = viewModel.getPresenter().getOrderResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_active_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActiveOrdersRecyclerViewAdapter(orderList, this));

        Button logOutButton = findViewById(R.id.btn_barista_actions_log_out); // "Log out" button
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onLogOutButton();
            }
        });

        Button refreshButton = findViewById(R.id.btn_barista_actions_refresh); // "Refresh" button
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaristaActionsActivity.this.onRefresh();
            }
        });
    }

    // -------
    // BaristaActionsView implementations
    // -------
    @Override
    public void onLogOut() {
        Intent intent = new Intent(BaristaActionsActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        finish();
        startActivity(getIntent());
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void viewOrder(Order o) {
        Intent intent = new Intent(BaristaActionsActivity.this, ManageOrderActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("cafe_brand", brand);
        intent.putExtra("table_number", o.getTable().getId());
        startActivity(intent);
    }
}
