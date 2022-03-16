package com.example.liveon_app.viewmodels;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.repositories.OrderRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderDetailViewModel extends ViewModel {

    private final OrderRepository orderRepository;
    private MutableLiveData<Order> order;

    public OrderDetailViewModel() {
        orderRepository = new OrderRepository();
        order = null;
    }

    public LiveData<Order> getOrder(Integer orderId) {
        Order mOrder = orderRepository.findById(orderId);

        if (mOrder != null) {
            order.postValue(mOrder);
        } else {
            fetchOrderData();
        }

        return order;
    }

    private void fetchOrderData() {

    }
}
