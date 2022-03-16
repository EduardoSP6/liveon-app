package com.example.liveon_app.models;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class VehicleImages extends RealmObject {

    private Signature signature;

    @Required
    private String image_url;

    public VehicleImages() {}

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
