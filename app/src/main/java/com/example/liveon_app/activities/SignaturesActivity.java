package com.example.liveon_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.liveon_app.R;
import com.example.liveon_app.adapters.SignatureListAdapter;
import com.example.liveon_app.databinding.ActivityMainBinding;
import com.example.liveon_app.databinding.ActivitySignaturesBinding;
import com.example.liveon_app.models.Order;
import com.example.liveon_app.viewmodels.ProfileViewModel;
import com.example.liveon_app.viewmodels.SignatureViewModel;

import java.util.ArrayList;
import java.util.List;

public class SignaturesActivity extends AppCompatActivity {

    private ActivitySignaturesBinding binding;
    private SignatureViewModel viewModel;
    private ArrayList<Order> ordersList = new ArrayList<>();

    private SignatureListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        binding = ActivitySignaturesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBackProfile.setOnClickListener(view1 -> {
            startActivity(new Intent(SignaturesActivity.this, MainActivity.class));
            finish();
        });

        viewModel = new ViewModelProvider(this).get(SignatureViewModel.class);

        RecyclerView mRecyclerView = binding.SignatureList;
        mAdapter = new SignatureListAdapter(getApplicationContext(), getSupportFragmentManager(), ordersList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        viewModel.getOrders().observe(this, this::updateList);
    }

    private void updateList(List<Order> orders) {

        ordersList.clear();

        if (orders.size() > 0) {
            ordersList.addAll(orders);
        }

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }
}