package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.Vehicle;
import com.example.liveon_app.models.VehicleImage;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class VehicleRepository {

    Realm realm = Realm.getDefaultInstance();

    public Vehicle create(Integer orderId, Integer year, String brand, String model, String engine
            , String engineType, String fuelType, Integer doorsQtd, Integer deliveryDay, Long km) {

        try {
            realm.beginTransaction();

            Order order = realm.where(Order.class)
                    .equalTo("order_id", orderId)
                    .findFirst();

            Vehicle vehicle = new Vehicle();
            vehicle.setUuid(UUID.randomUUID().toString());
            vehicle.setOrder(order);
            vehicle.setYear(year);
            vehicle.setBrand(brand);
            vehicle.setModel(model);
            vehicle.setEngine(engine);
            vehicle.setEngineType(engineType);
            vehicle.setFuelType(fuelType);
            vehicle.setDoorsQtd(doorsQtd);
            vehicle.setDeliveryDelay(deliveryDay);
            vehicle.setKm(km);

            realm.copyToRealm(vehicle);
            realm.commitTransaction();

            return vehicle;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }

        return null;
    }

    public Vehicle setVehicleImages(String vehicleUuid) {
        try {
            realm.beginTransaction();

            Vehicle vehicle = realm.where(Vehicle.class)
                    .equalTo("uuid", vehicleUuid)
                    .findFirst();

            if (vehicle != null) {
                RealmResults<VehicleImage> vehicleImages = realm
                        .where(VehicleImage.class)
                        .findAll();

                for (VehicleImage image : vehicleImages) {
                    vehicle.getVehicleImages().add(image);
                }

                realm.copyToRealmOrUpdate(vehicle);
            }

            realm.commitTransaction();

            return vehicle;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }

        return null;
    }
}
