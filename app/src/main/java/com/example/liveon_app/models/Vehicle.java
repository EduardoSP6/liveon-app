package com.example.liveon_app.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Vehicle extends RealmObject {

    @PrimaryKey
    private String uuid;

    @SerializedName("vehicle_year")
    private Integer year;

    @SerializedName("vehicle_brand")
    private String brand;

    @SerializedName("vehicle_model")
    private String model;

    private String engine;

    @SerializedName("engine_type")
    private String engineType;

    @SerializedName("fuel_type")
    private String fuelType;

    @SerializedName("doors_qtd")
    private Integer doorsQtd;

    @SerializedName("delivery_delay")
    private Integer deliveryDelay;

    private Long km;

    private Order order;

    private RealmList<VehicleImage> vehicleImages;

    public Vehicle() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getDoorsQtd() {
        return doorsQtd;
    }

    public void setDoorsQtd(Integer doorsQtd) {
        this.doorsQtd = doorsQtd;
    }

    public Integer getDeliveryDelay() {
        return deliveryDelay;
    }

    public void setDeliveryDelay(Integer deliveryDelay) {
        this.deliveryDelay = deliveryDelay;
    }

    public Long getKm() {
        return km;
    }

    public void setKm(Long km) {
        this.km = km;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public RealmList<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(RealmList<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }
}
