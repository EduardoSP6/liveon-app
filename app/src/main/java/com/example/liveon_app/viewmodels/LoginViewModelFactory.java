package com.example.liveon_app.viewmodels;

import com.example.liveon_app.interfaces.LoginResultCallback;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginResultCallback loginResultCallback;

    public LoginViewModelFactory(LoginResultCallback loginResultCallback) {
        this.loginResultCallback = loginResultCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LoginViewModel(loginResultCallback);
    }
}
