package com.example.liveon_app.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class OrderValue extends RealmObject {

    @PrimaryKey
    private String uuid;

    @SerializedName("monthly_price")
    private String monthlyPrice;

    private String extras;

    @SerializedName("total_price")
    private String totalPrice;

    private Order order;

    public OrderValue() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
