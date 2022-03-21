package com.example.liveon_app.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class VehicleImage extends RealmObject {

    @SerializedName("image_url")
    private String imageUrl;

    private Vehicle vehicle;

    public VehicleImage() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
