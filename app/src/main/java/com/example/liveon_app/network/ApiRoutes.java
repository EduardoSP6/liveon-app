package com.example.liveon_app.network;

import com.example.liveon_app.network.requests.AuthRequest;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiRoutes {

    @POST("auth")
    Call<AuthRequest> login();
}
