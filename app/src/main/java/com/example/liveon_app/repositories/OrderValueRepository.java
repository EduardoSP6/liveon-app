package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderValue;

import java.util.UUID;

import io.realm.Realm;
import io.realm.exceptions.RealmException;

public class OrderValueRepository {

    Realm realm = Realm.getDefaultInstance();

    public OrderValue create(Integer orderId, String monthlyPrice, String extras, String totalPrice) {
        try {
            realm.beginTransaction();

            Order order = realm.where(Order.class)
                    .equalTo("order_id", orderId)
                    .findFirst();

            OrderValue orderValue = new OrderValue();
            orderValue.setUuid(UUID.randomUUID().toString());
            orderValue.setOrder(order);
            orderValue.setMonthlyPrice(monthlyPrice);
            orderValue.setExtras(extras);
            orderValue.setTotalPrice(totalPrice);

            realm.copyToRealm(orderValue);
            realm.commitTransaction();

            return orderValue;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
            return null;
        }
    }
}
