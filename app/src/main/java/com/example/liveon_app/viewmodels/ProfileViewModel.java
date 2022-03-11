package com.example.liveon_app.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderStatus;
import com.example.liveon_app.models.User;
import com.example.liveon_app.network.ApiRoutes;
import com.example.liveon_app.network.responses.ProfileResponse;
import com.example.liveon_app.repositories.OrderRepository;
import com.example.liveon_app.repositories.UserRepository;
import com.example.liveon_app.utils.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileViewModel extends ViewModel {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final MutableLiveData<List<Order>> orders;

    public ProfileViewModel() {
        userRepository = new UserRepository();
        orderRepository = new OrderRepository();
        orders = new MutableLiveData<List<Order>>();
    }

    public User getUser() {
        return userRepository.getAuthenticated();
    }

    public MutableLiveData<List<Order>> getOrders() {

        List<Order> ordersList = loadOrders();

        if (ordersList.size() > 0) {
            orders.postValue(ordersList);
        } else {
            fetchOrders();
        }

        return orders;
    }

    public List<OrderStatus> getOrderStatusList(Order order) {
        return orderRepository.getOrderStatuses(order);
    }

    public boolean logout(User user) {
        return userRepository.destroy(user);
    }

    private List<Order> loadOrders() {
        return orderRepository.getUserOrders(getUser().getUsername());
    }

    private void fetchOrders() {

        Retrofit retrofit = new RetrofitConfig().getRetrofit(getUser().getToken());

        Call<ProfileResponse> call = retrofit.create(ApiRoutes.class).getProfile();
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                ProfileResponse responseBody = response.body();

                if (response.isSuccessful() && responseBody != null && responseBody.getSuccess()) {
                    createProfile(responseBody);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                Log.d("DEV", "profile request error: " + t.getMessage());
                t.printStackTrace();
                orders.postValue(null);
            }
        });
    }

    private void createProfile(ProfileResponse data) {
        for (Order order : data.getOrders()) {
            orderRepository.create(
                    order.getOrder_id(),
                    order.getSubmodel_name(),
                    data.getUsername(),
                    order.getStatuses()
            );
        }

        loadOrders();
    }
}
