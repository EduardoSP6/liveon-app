package com.example.liveon_app.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Order extends RealmObject {

    @PrimaryKey
    private Integer order_id;
    private String submodel_name;

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
}
