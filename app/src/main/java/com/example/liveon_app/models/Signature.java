package com.example.liveon_app.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Signature extends RealmObject {

    @PrimaryKey
    private String uuid;

    private Integer months;

    @SerializedName("plan_type")
    private Integer planType;

    @SerializedName("additional_franchise")
    private String additionalFranchise;

    private Order order;

    public Signature() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getAdditionalFranchise() {
        return additionalFranchise;
    }

    public void setAdditionalFranchise(String additionalFranchise) {
        this.additionalFranchise = additionalFranchise;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
