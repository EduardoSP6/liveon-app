package com.example.liveon_app.network;

import com.example.liveon_app.network.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRoutes {

    @POST("auth")
    Call<AuthResponse> login(@Query("email") String email, @Query("password") String password);
}
