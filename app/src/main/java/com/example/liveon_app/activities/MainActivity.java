package com.example.liveon_app.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.liveon_app.R;
import com.example.liveon_app.databinding.ActivityMainBinding;
import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.User;
import com.example.liveon_app.viewmodels.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ProfileViewModel viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        User user = viewModel.getUser();

        Picasso.get().setLoggingEnabled(false);
        Picasso.get().load(user.getAvatar_url()).fit().into(binding.imgAvatar);

        binding.txtUsername.setText(user.getFullname());

        String cityAndState = user.getCity() +" - "+ user.getState_abbr();
        binding.txtCity.setText(cityAndState);

        viewModel.getOrders().observe(this, this::buildTextSwitcher);
    }

    private void buildTextSwitcher(List<Order> orders) {
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
            }

            Order order = orders.get(position.intValue());

            binding.txtSwitcher.setText(order.getSubmodel_name());

            updateOrderData(order);
        });
    }

    private void updateOrderData(Order order) {

    }
}