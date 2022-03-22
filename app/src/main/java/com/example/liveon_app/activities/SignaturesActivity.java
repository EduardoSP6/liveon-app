package com.example.liveon_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.liveon_app.adapters.SignatureListAdapter;
import com.example.liveon_app.databinding.ActivitySignaturesBinding;
import com.example.liveon_app.viewmodels.SignatureViewModel;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SignaturesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        com.example.liveon_app.databinding.ActivitySignaturesBinding binding = ActivitySignaturesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnBackProfile.setOnClickListener(view1 -> {
            startActivity(new Intent(SignaturesActivity.this, MainActivity.class));
            finish();
        });

        SignatureViewModel viewModel = new ViewModelProvider(this).get(SignatureViewModel.class);

        RecyclerView mRecyclerView = binding.SignatureList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        SignatureListAdapter mAdapter = new SignatureListAdapter(SignaturesActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        viewModel.getOrders().observe(this, mAdapter::setOrders);
    }
}