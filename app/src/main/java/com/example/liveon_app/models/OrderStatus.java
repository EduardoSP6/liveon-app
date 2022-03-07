package com.example.liveon_app.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class OrderStatus extends RealmObject {

    @PrimaryKey
    private String uuid;
    private Integer order_id;
    private String code;
    private String label;
    private Boolean checked;

    public OrderStatus() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
