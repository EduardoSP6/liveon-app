package com.example.liveon_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.liveon_app.R;
import com.example.liveon_app.databinding.ActivityMainBinding;
import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderStatus;
import com.example.liveon_app.models.User;
import com.example.liveon_app.viewmodels.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        User user = viewModel.getUser();

        Picasso.get().setLoggingEnabled(false);
        Picasso.get().load(user.getAvatar_url()).fit().into(binding.imgAvatar);

        binding.txtUsername.setText(user.getFullname());

        String cityAndState = user.getCity() +" - "+ user.getState_abbr();
        binding.txtCity.setText(cityAndState);

        binding.btnPrevius.setEnabled(false);

        viewModel.getOrders().observe(this, this::buildOrderStatusComponents);

        binding.btnSignatures.setOnClickListener(view12 -> {
            startActivity(new Intent(MainActivity.this, SignaturesActivity.class));
            finish();
        });

        binding.btnLogout.setOnClickListener(view1 -> {
            if (viewModel.logout(user)) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void buildOrderStatusComponents(List<Order> orders) {
        AtomicInteger position = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();

        count.set(orders.size());

        binding.txtSwitcher.setFactory(() -> {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(orders.get(position.intValue()).getSubmodel_name());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(this, R.color.black));
            return textView;
        });

        binding.btnNext.setOnClickListener(view -> {
            position.set(position.getAndIncrement());

            if (position.equals(count)) {
                position.set(0);
                binding.btnPrevius.setEnabled(false);
            }

            Order order = orders.get(position.intValue());

            binding.txtSwitcher.setText(order.getSubmodel_name());

            updateOrderData(order);
        });

        binding.btnPrevius.setOnClickListener(view -> {
            binding.txtSwitcher.showPrevious();
            position.set(position.decrementAndGet());

            if (position.intValue() < 0) {
                position.set( (orders.size() - 1) );
                binding.btnNext.setEnabled(false);
            }

            Order order = orders.get(position.intValue());

            binding.txtSwitcher.setText(order.getSubmodel_name());

            updateOrderData(order);
        });

        if (orders.size() > 0 && position.intValue() == 0) {
            updateOrderData(orders.get(0));
        }
    }

    private void updateOrderData(Order order) {

        List<OrderStatus> orderStatuses = viewModel.getOrderStatusList(order);

        binding.rdGroupStatus.removeAllViews();

        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 30, 0, 0);

        for (OrderStatus orderStatus: orderStatuses) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(params);
            radioButton.setText(orderStatus.getLabel());
            radioButton.setChecked(orderStatus.getChecked());
            radioButton.setClickable(false);

            if (orderStatus.getChecked()) {
                radioButton.setButtonDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_24));
            }

            binding.rdGroupStatus.addView(radioButton);
        }
    }
}