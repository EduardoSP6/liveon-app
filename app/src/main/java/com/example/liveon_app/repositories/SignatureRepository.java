package com.example.liveon_app.repositories;

import com.example.liveon_app.models.Order;
import com.example.liveon_app.models.Signature;

import java.util.UUID;

import io.realm.Realm;
import io.realm.exceptions.RealmException;

public class SignatureRepository {

    Realm realm = Realm.getDefaultInstance();

    public Signature create(Integer orderId, Integer months, Integer planType, String additionalFranchise) {
        try {
            realm.beginTransaction();

            Order order = realm.where(Order.class)
                    .equalTo("order_id", orderId)
                    .findFirst();

            Signature signature = new Signature();
            signature.setUuid(UUID.randomUUID().toString());
            signature.setOrder(order);
            signature.setMonths(months);
            signature.setPlanType(planType);
            signature.setAdditionalFranchise(additionalFranchise);

            realm.copyToRealm(signature);
            realm.commitTransaction();

            return signature;

        } catch (RealmException e) {
            realm.cancelTransaction();
            e.printStackTrace();
            return null;
        }
    }
}
