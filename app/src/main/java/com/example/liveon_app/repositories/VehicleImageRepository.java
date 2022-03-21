package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Vehicle;
import com.example.liveon_app.models.VehicleImage;

import io.realm.Realm;
import io.realm.exceptions.RealmException;

public class VehicleImageRepository {

    Realm realm = Realm.getDefaultInstance();

    public VehicleImage create(String vehicleUuid, String imageUrl) {
        try {
            realm.beginTransaction();

            Vehicle vehicle = realm.where(Vehicle.class)
                    .equalTo("uuid", vehicleUuid)
                    .findFirst();

            VehicleImage vehicleImage = new VehicleImage();
            vehicleImage.setVehicle(vehicle);
            vehicleImage.setImageUrl(imageUrl);

            realm.copyToRealm(vehicleImage);
            realm.commitTransaction();

            return vehicleImage;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAll() {
        realm.executeTransaction(realm -> realm.delete(VehicleImage.class));
    }
}
