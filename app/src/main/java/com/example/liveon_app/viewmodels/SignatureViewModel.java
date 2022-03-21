package com.example.liveon_app.viewmodels;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.User;
import com.example.liveon_app.repositories.OrderRepository;
import com.example.liveon_app.repositories.UserRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignatureViewModel extends ViewModel {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final MutableLiveData<List<Order>> orders;

    public SignatureViewModel() {
        userRepository = new UserRepository();
        orderRepository = new OrderRepository();
        orders = new MutableLiveData<>();
    }

    public User getUser() {
        return userRepository.getAuthenticated();
    }

    public MutableLiveData<List<Order>> getOrders() {
        List<Order> ordersList = orderRepository.getUserOrders(getUser().getUsername());
        orders.postValue(ordersList);
        return orders;
    }
}
