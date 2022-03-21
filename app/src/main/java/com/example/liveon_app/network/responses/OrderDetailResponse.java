package com.example.liveon_app.network.responses;

import com.example.liveon_app.models.OrderValue;
import com.example.liveon_app.models.Signature;
import com.google.gson.annotations.SerializedName;

public class OrderDetailResponse {

    @SerializedName("vehicle_details")
    private VehicleDetailResponse vehicleDetails;

    @SerializedName("signature_details")
    private Signature signatureDetails;

    @SerializedName("summary_values")
    private OrderValue summaryValues;

    public VehicleDetailResponse getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetailResponse vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public Signature getSignatureDetails() {
        return signatureDetails;
    }

    public void setSignatureDetails(Signature signatureDetails) {
        this.signatureDetails = signatureDetails;
    }

    public OrderValue getSummaryValues() {
        return summaryValues;
    }

    public void setSummaryValues(OrderValue summaryValues) {
        this.summaryValues = summaryValues;
    }
}
