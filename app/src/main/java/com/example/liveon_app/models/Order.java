package com.example.liveon_app.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Order extends RealmObject {

    @PrimaryKey
    private Integer order_id;
    private String submodel_name;
    private String username;
    private RealmList<OrderStatus> statuses;

    private Vehicle vehicle;

    private Signature signature;

    private OrderValue orderValue;

    public Order() {}

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getSubmodel_name() {
        return submodel_name;
    }

    public void setSubmodel_name(String submodel_name) {
        this.submodel_name = submodel_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RealmList<OrderStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(RealmList<OrderStatus> statuses) {
        this.statuses = statuses;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public OrderValue getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(OrderValue orderValue) {
        this.orderValue = orderValue;
    }
}
