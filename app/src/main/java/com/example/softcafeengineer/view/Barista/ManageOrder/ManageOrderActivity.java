package com.example.softcafeengineer.view.Barista.ManageOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.domain.OrderInfo;

import java.util.List;

public class ManageOrderActivity extends AppCompatActivity implements ManageOrderView, OrderListRecyclerViewAdapter.ItemSelectionListener
{
    private ManageOrderViewModel viewModel;
    private String brand;
    private int table_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            brand = intent.getStringExtra("cafe_brand");
            table_number = intent.getIntExtra("table_number", -1);
        }

        viewModel = new ViewModelProvider(this).get(ManageOrderViewModel.class);
        viewModel.getPresenter().setView(this);

        List<OrderInfo> orderList = viewModel.getPresenter().getOrderResults(brand, table_number);

        TextView total_cost = findViewById(R.id.manage_order_total_cost);
        total_cost.setText(String.format("%f 💶", viewModel.getPresenter().getTotalCost()));

        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderListRecyclerViewAdapter(orderList, this));
    }



    // -------
    // ManageOrderView implementations
    // -------


    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void manageOrderInfo(OrderInfo o) {
    }
}
