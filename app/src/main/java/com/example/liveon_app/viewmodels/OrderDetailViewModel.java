package com.example.liveon_app.viewmodels;

import android.util.Log;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderValue;
import com.example.liveon_app.models.Signature;
import com.example.liveon_app.models.User;
import com.example.liveon_app.models.Vehicle;
import com.example.liveon_app.network.ApiRoutes;
import com.example.liveon_app.network.responses.OrderDetailResponse;
import com.example.liveon_app.network.responses.VehicleDetailResponse;
import com.example.liveon_app.repositories.OrderRepository;
import com.example.liveon_app.repositories.OrderValueRepository;
import com.example.liveon_app.repositories.SignatureRepository;
import com.example.liveon_app.repositories.UserRepository;
import com.example.liveon_app.repositories.VehicleImageRepository;
import com.example.liveon_app.repositories.VehicleRepository;
import com.example.liveon_app.utils.RetrofitConfig;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetailViewModel extends ViewModel {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SignatureRepository signatureRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleImageRepository vehicleImageRepository;
    private final OrderValueRepository orderValueRepository;
    private final MutableLiveData<Order> order;

    private Integer orderId;

    public OrderDetailViewModel() {
        orderRepository = new OrderRepository();
        userRepository = new UserRepository();
        signatureRepository = new SignatureRepository();
        vehicleRepository = new VehicleRepository();
        vehicleImageRepository = new VehicleImageRepository();
        orderValueRepository = new OrderValueRepository();
        order = new MutableLiveData<>();
    }

    public User getUser() {
        return userRepository.getAuthenticated();
    }

    public LiveData<Order> getOrderDetail(Integer orderId) {

        setOrderId(orderId);

        Order mOrder = loadOrder();

        if (mOrder.getVehicle() != null
                && mOrder.getSignature() != null
                && mOrder.getOrderValue() != null) {
            order.postValue(mOrder);
        } else {
            fetchOrderData(orderId);
        }

        return order;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    private Order loadOrder() {
        return orderRepository.findById(getOrderId());
    }

    private void fetchOrderData(Integer orderId) {

        Retrofit retrofit = new RetrofitConfig().getRetrofit(getUser().getToken());

        Call<OrderDetailResponse> call = retrofit.create(ApiRoutes.class).getOrderDetail(orderId);
        call.enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<OrderDetailResponse> call, @NonNull Response<OrderDetailResponse> response) {
                OrderDetailResponse responseBody = response.body();

                if (response.isSuccessful() && responseBody != null) {
                    createRecords(responseBody);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderDetailResponse> call, @NonNull Throwable t) {
                Log.d("DEV", "order_detail request error: " + t.getMessage());
                t.printStackTrace();
                order.postValue(null);
            }
        });
    }

    private void createRecords(OrderDetailResponse data) {

        Order existOrder = loadOrder();

        VehicleDetailResponse vehicleDetails = data.getVehicleDetails();
        Signature signatureDetails = data.getSignatureDetails();
        OrderValue orderValueDetail = data.getSummaryValues();

        Vehicle vehicle = vehicleRepository.create(
                existOrder.getOrder_id(),
                vehicleDetails.getYear(),
                vehicleDetails.getBrand(),
                vehicleDetails.getModel(),
                vehicleDetails.getEngine(),
                vehicleDetails.getEngineType(),
                vehicleDetails.getFuelType(),
                vehicleDetails.getDoorsQtd(),
                vehicleDetails.getDeliveryDelay(),
                vehicleDetails.getKm()
        );

        vehicleImageRepository.deleteAll();
        for (String imageUrl : vehicleDetails.getImageUrl()) {
            vehicleImageRepository.create(vehicle.getUuid(), imageUrl);
        }

        vehicle = vehicleRepository.setVehicleImages(vehicle.getUuid());

        Signature signature = signatureRepository.create(
                existOrder.getOrder_id(),
                signatureDetails.getMonths(),
                signatureDetails.getPlanType(),
                signatureDetails.getAdditionalFranchise()
        );

        OrderValue orderValue = orderValueRepository.create(
                existOrder.getOrder_id(),
                orderValueDetail.getMonthlyPrice(),
                orderValueDetail.getExtras(),
                orderValueDetail.getTotalPrice()
        );

        existOrder = orderRepository.setOrderVehicle(existOrder, vehicle.getUuid());

        existOrder = orderRepository.setOrderSignature(existOrder, signature.getUuid());

        existOrder = orderRepository.setOrderValues(existOrder, orderValue.getUuid());

        order.postValue(existOrder);
    }
}
