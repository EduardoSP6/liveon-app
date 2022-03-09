package com.example.liveon_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.liveon_app.databinding.ActivityLoginBinding;
import com.example.liveon_app.interfaces.LoginResultCallback;
import com.example.liveon_app.viewmodels.LoginViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity implements LoginResultCallback {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        this.setTitle("LiveOn");

        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (viewModel.isAuthenticated()) {
            closeActivity();
        }

        binding.btnLogin.setOnClickListener(view1 -> {
            String email = binding.inputEmail.getText().toString();
            String password = binding.inputPassword.getText().toString();

            final ProgressDialog progress = new ProgressDialog(this);
            progress.setTitle("Carregando...");
            progress.setMessage("");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setMax(100);
            progress.setCancelable(false);
            progress.setCanceledOnTouchOutside(false);
            progress.show();

            new Thread() {
                @Override
                public void run() {
                    try {
                        viewModel.authUser(email, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        progress.dismiss();
                    }
                }
            }.start();
        });
    }

    @Override
    public void onSuccess(String message) {
        closeActivity();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void closeActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}