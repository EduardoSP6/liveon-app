package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.OrderStatus;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class OrderRepository {

    Realm realm = Realm.getDefaultInstance();

    public List<Order> getUserOrders(String username) {
        return realm.where(Order.class).equalTo("username", username).findAll();
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
}
