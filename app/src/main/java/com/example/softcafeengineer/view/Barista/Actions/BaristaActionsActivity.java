package com.example.softcafeengineer.view.Barista.Actions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.Order;

import java.util.List;

public class BaristaActionsActivity extends AppCompatActivity implements BaristaActionsView, ActiveOrdersRecyclerViewAdapter.ItemSelectionListener
{
    private BaristaActionsViewModel viewModel;
    private String username, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barista_actions);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
            brand = intent.getStringExtra("cafe_brand");
        }
        TextView header = findViewById(R.id.txt_barista_actions_header);
        header.setText(String.format("Hello, %s!", username));

        viewModel = new ViewModelProvider(this).get(BaristaActionsViewModel.class);
        viewModel.getPresenter().setView(this);

        List<Order> orderList = viewModel.getPresenter().getOrderResults();
        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_active_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActiveOrdersRecyclerViewAdapter(orderList, this));


    }

    // -------
    // BaristaActionsView implementations
    // -------

    // -------
    // ItemSelectionListener implementations
    // -------
}
