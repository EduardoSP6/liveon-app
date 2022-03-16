package com.example.liveon_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.liveon_app.R;
import com.example.liveon_app.databinding.ActivityMainBinding;
import com.example.liveon_app.databinding.ActivityOrderDetailBinding;

public class OrderDetailActivity extends AppCompatActivity {

    private ActivityOrderDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}