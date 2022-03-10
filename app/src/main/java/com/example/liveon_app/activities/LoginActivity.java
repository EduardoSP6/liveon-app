package com.example.liveon_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.liveon_app.databinding.ActivityLoginBinding;
import com.example.liveon_app.interfaces.LoginResultCallback;
import com.example.liveon_app.viewmodels.LoginViewModel;
import com.example.liveon_app.viewmodels.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity implements LoginResultCallback {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        LoginViewModel viewModel = new ViewModelProvider(this,
                new LoginViewModelFactory(this))
                .get(LoginViewModel.class);

        if (viewModel.isAuthenticated()) {
            redirectUser();
        }

        binding.btnLogin.setOnClickListener(view1 -> {
            String email = binding.inputEmail.getText().toString();
            String password = binding.inputPassword.getText().toString();

            viewModel.login(email, password);
        });
    }

    @Override
    public void onSuccess(String message) {
        redirectUser();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void redirectUser() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}