package com.example.liveon_app.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleDetailResponse {

    @SerializedName("image_url")
    private List<String> imageUrl;

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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
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
}
