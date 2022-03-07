package com.example.liveon_app.models;

import java.math.BigInteger;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Signature extends RealmObject {

    @PrimaryKey
    private String uuid;
    private Integer vehicle_year;
    private String vehicle_brand;
    private String vehicle_model;
    private String engine;
    private String engine_type;
    private String fuel_type;
    private Integer doors_qtd;
    private Integer delivery_delay;
    private BigInteger km;
    private Integer months;
    private Integer plan_type;
    private String additional_franchise;
    private String monthly_price;
    private String extras;
    private String total_price;

    private RealmList<VehicleImages> vehicleImages;

    public Signature() {}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getVehicle_year() {
        return vehicle_year;
    }

    public void setVehicle_year(Integer vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getVehicle_brand() {
        return vehicle_brand;
    }

    public void setVehicle_brand(String vehicle_brand) {
        this.vehicle_brand = vehicle_brand;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Integer getDoors_qtd() {
        return doors_qtd;
    }

    public void setDoors_qtd(Integer doors_qtd) {
        this.doors_qtd = doors_qtd;
    }

    public Integer getDelivery_delay() {
        return delivery_delay;
    }

    public void setDelivery_delay(Integer delivery_delay) {
        this.delivery_delay = delivery_delay;
    }

    public BigInteger getKm() {
        return km;
    }

    public void setKm(BigInteger km) {
        this.km = km;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(Integer plan_type) {
        this.plan_type = plan_type;
    }

    public String getAdditional_franchise() {
        return additional_franchise;
    }

    public void setAdditional_franchise(String additional_franchise) {
        this.additional_franchise = additional_franchise;
    }

    public String getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(String monthly_price) {
        this.monthly_price = monthly_price;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public RealmList<VehicleImages> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(RealmList<VehicleImages> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }
}
