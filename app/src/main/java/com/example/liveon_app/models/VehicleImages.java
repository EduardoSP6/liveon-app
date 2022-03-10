package com.example.liveon_app.models;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class VehicleImages extends RealmObject {

    private Signature signature;

    @Required
    private String url;

    public VehicleImages() {}

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
