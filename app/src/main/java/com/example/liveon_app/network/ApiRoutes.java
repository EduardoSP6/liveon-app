package com.example.liveon_app.network;

import com.example.liveon_app.network.responses.AuthResponse;
import com.example.liveon_app.network.responses.OrderDetailResponse;
import com.example.liveon_app.network.responses.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRoutes {

    @POST("auth")
    Call<AuthResponse> login(@Query("email") String email, @Query("password") String password);

    @GET("user/profile")
    Call<ProfileResponse> getProfile();

    @GET("user/profile/order_details")
    Call<OrderDetailResponse> getOrderDetail(@Query("order_id") Integer orderId);
}
