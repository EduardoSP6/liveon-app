package com.example.liveon_app.repositories;

import android.util.Log;

import com.example.liveon_app.models.Signature;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class SignatureRepository {

    public List<Signature> getUserSignatures(String username) {
        Realm realm = Realm.getDefaultInstance();
        List<Signature> signatures = realm.where(Signature.class)
                .equalTo("username", username.trim())
                .findAll();
        realm.close();
        return signatures;
    }

    public void create(Integer vehicle_year, String vehicle_brand, String vehicle_model,
                       String engine, String engine_type, String fuel_type, Integer doors_qtd,
                       Integer delivery_delay, String km, Integer months, Integer plan_type,
                       String additional_franchise, String monthly_price, String extras,
                       String total_price, String username) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();

            Signature signature = new Signature();
            signature.setUuid(UUID.randomUUID().toString());
            signature.setVehicle_year(vehicle_year);
            signature.setVehicle_brand(vehicle_brand);
            signature.setVehicle_model(vehicle_model);
            signature.setEngine(engine);
            signature.setEngine_type(engine_type);
            signature.setFuel_type(fuel_type);
            signature.setDoors_qtd(doors_qtd);
            signature.setDelivery_delay(delivery_delay);
            signature.setKm(km);
            signature.setMonths(months);
            signature.setPlan_type(plan_type);
            signature.setAdditional_franchise(additional_franchise);
            signature.setMonthly_price(monthly_price);
            signature.setExtras(extras);
            signature.setTotal_price(total_price);
            signature.setUsername(username);

            realm.copyToRealm(signature);
            realm.commitTransaction();

        } catch (Exception e) {
            Log.e("DEV", "Signature create error: "+e.getMessage());
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }
}
