package com.example.softcafeengineer.view.Order.ViewCart;

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
import com.example.softcafeengineer.view.Barista.ManageOrder.OrderListRecyclerViewAdapter;
import com.example.softcafeengineer.view.StartScreens.WelcomeScreenActivity;

import java.util.List;

public class ViewCartActivity extends AppCompatActivity implements ViewCartView, OrderListRecyclerViewAdapter.ItemSelectionListener
{
    private ViewCartViewModel viewModel;
    private String unique_id;
    private RelativeLayout relativeLayout;

    // Edit order info pop up
    private OrderInfo selected_order_info;
    private PopupWindow edit_order_info_popup;
    private String prev_comments;
    private int prev_quantity;
    private EditText editQuantityField, editCommentsField;
    private Button confirmEditButton;
    private boolean confirm_edit_enabled, text_changed;
    private String newQuantity, newComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_view_cart); // activity_view_cart.xml layout

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            unique_id = intent.getStringExtra("unique_id");
        }

        viewModel = new ViewModelProvider(this).get(ViewCartViewModel.class);
        viewModel.getPresenter().setView(this, unique_id);

        List<OrderInfo> orderList = viewModel.getPresenter().getOrderResults();

        TextView total_cost = findViewById(R.id.cart_total_cost);
        total_cost.setText(String.format("%.2f 💶", viewModel.getPresenter().getTotalCost()));

        // Recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderListRecyclerViewAdapter(orderList, this));

        Button submitOrderButton = findViewById(R.id.btn_submit_order);
        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onSubmitOrder();
            }
        });
    }

    // -------
    // ViewCartView implementations
    // -------
    @Override
    public void onSuccessfulSubmitOrder() {
        Intent intent = new Intent(ViewCartActivity.this, WelcomeScreenActivity.class);
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
        Toast.makeText(ViewCartActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // -------
    // ItemSelectionListener implementations
    // -------
    @Override
    public void editOrderInfo(OrderInfo orderInfo) {
        selected_order_info = orderInfo;
        prev_quantity = orderInfo.getQuantity();
        prev_comments = orderInfo.getDescription();
        // Inflate popup layout
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View pop_up = layoutInflater.inflate(R.layout.popup_add_to_cart, null);

        // Create and show edit order info popup
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        edit_order_info_popup = new PopupWindow(pop_up, width, height, true);
        edit_order_info_popup.showAtLocation(relativeLayout, Gravity.CENTER, 0,0);

        editQuantityField = pop_up.findViewById(R.id.edit_text_choose_quantity);
        editQuantityField.setText(String.format("%d", prev_quantity));
        editCommentsField = pop_up.findViewById(R.id.edit_text_comments);
        editCommentsField.setText(prev_comments);
        editQuantityField.addTextChangedListener(editOrderInfoWatcher);
        editCommentsField.addTextChangedListener(editOrderInfoWatcher);

        confirmEditButton = pop_up.findViewById(R.id.btn_final_add_to_cart);
        confirmEditButton.setOnClickListener(onConfirmEditButton);
        // Confirm button is enabled
        confirm_edit_enabled = true;
        confirmEditButton.setAlpha(1.0f);
        text_changed = false;

        Button cancelButton = pop_up.findViewById(R.id.btn_cancel_add_to_cart);
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
            newComments = editCommentsField.getText().toString();
            if(!newQuantity.isEmpty()) {
                // Comments are not required
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
            viewModel.getPresenter().onEditProductInfo(selected_order_info, confirm_edit_enabled, text_changed, prev_quantity, prev_comments,  newQuantity, newComments);
        }
    };
}
