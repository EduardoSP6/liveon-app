package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderStatus;
import com.example.liveon_app.models.OrderValue;
import com.example.liveon_app.models.Signature;
import com.example.liveon_app.models.Vehicle;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.exceptions.RealmException;

public class OrderRepository {

    Realm realm = Realm.getDefaultInstance();

    public List<Order> getUserOrders(String username) {
        return realm.where(Order.class).equalTo("username", username).findAll();
    }

    public Order findById(Integer id) {
        return realm.where(Order.class).equalTo("order_id", id).findFirst();
    }

    public Order create(Integer order_id, String submodel_name, String username, List<OrderStatus> statuses) {
        try {
            realm.beginTransaction();

            Order order = new Order();
            order.setOrder_id(order_id);
            order.setSubmodel_name(submodel_name);
            order.setUsername(username);

            realm.copyToRealm(order);

            for (OrderStatus orderStatus : statuses) {
                orderStatus.setUuid(UUID.randomUUID().toString());
                orderStatus.setOrder_id(order.getOrder_id());

                realm.copyToRealm(orderStatus);
            }

            realm.commitTransaction();

            return order;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderStatus> getOrderStatuses(Order order) {
        return realm.where(OrderStatus.class)
                .equalTo("order_id", order.getOrder_id())
                .findAll();
    }

    public Order setOrderVehicle(Order order, String vehicleUuid) {
        try {
            realm.beginTransaction();

            Vehicle vehicle = realm.where(Vehicle.class)
                    .equalTo("uuid", vehicleUuid)
                    .findFirst();

            order.setVehicle(vehicle);

            realm.copyToRealmOrUpdate(order);
            realm.commitTransaction();

            return order;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return null;
    }

    public Order setOrderSignature(Order order, String signatureUuid) {
        try {
            realm.beginTransaction();

            Signature signature = realm.where(Signature.class)
                    .equalTo("uuid", signatureUuid)
                    .findFirst();

            order.setSignature(signature);

            realm.copyToRealmOrUpdate(order);
            realm.commitTransaction();

            return order;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return null;
    }

    public Order setOrderValues(Order order, String orderValueUuid) {
        try {
            realm.beginTransaction();

            OrderValue orderValue = realm.where(OrderValue.class)
                    .equalTo("uuid", orderValueUuid)
                    .findFirst();

            order.setOrderValue(orderValue);

            realm.copyToRealmOrUpdate(order);
            realm.commitTransaction();

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
        return null;
    }
}
