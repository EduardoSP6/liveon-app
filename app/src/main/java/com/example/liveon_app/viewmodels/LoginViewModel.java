package com.example.liveon_app.viewmodels;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.example.liveon_app.interfaces.LoginResultCallback;
import com.example.liveon_app.network.ApiRoutes;
import com.example.liveon_app.network.responses.AuthResponse;
import com.example.liveon_app.repositories.UserRepository;
import com.example.liveon_app.utils.RetrofitConfig;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<AuthResponse> authUserLiveData;
    private final UserRepository userRepository;

    private String email;
    private String password;
    private LoginResultCallback loginResultCallback;

    public LoginViewModel(LoginResultCallback loginResultCallback) {
        this.loginResultCallback = loginResultCallback;
        authUserLiveData = new MutableLiveData<>();
        userRepository = new UserRepository();
    }

    public MutableLiveData<AuthResponse> getAuthUserLiveData() {
        return authUserLiveData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailValid() {
        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordValid() {
        return getPassword().length() > 6;
    }

    public boolean isAuthenticated() {
        return userRepository.getAuthenticated() != null;
    }

    public void authUser(String email, String password) {

        setEmail(email);
        setPassword(password);

        if (!isEmailValid()) {
            loginResultCallback.onError("E-mail inválido!");
            return;
        } else if (!isPasswordValid()) {
            loginResultCallback.onError("A senha deve conter ao menos 7 caracteres");
            return;
        }

        Retrofit retrofit = new RetrofitConfig().getRetrofit("");

        Call<AuthResponse> call = retrofit.create(ApiRoutes.class).login(getEmail(), getPassword());
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                AuthResponse responseBody = response.body();

                if (response.isSuccessful() && responseBody != null && responseBody.getSuccess()) {
                    createUser(responseBody);
                    authUserLiveData.postValue(responseBody);
                    loginResultCallback.onSuccess("Sucesso");
                } else {
                    authUserLiveData.postValue(null);
                    loginResultCallback.onError("Credenciais inválidas! Tente novamente");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                authUserLiveData.postValue(null);
                loginResultCallback.onError("Falha na autenticação! Tente mais tarde.");

                Log.d("DEV", "Login request error: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void createUser(AuthResponse data) {
        userRepository.create(
                data.getUsername(),
                data.getFullname(),
                data.getAvatar_url(),
                data.getCity(),
                data.getState_abbr(),
                data.getToken()
        );
    }
}
