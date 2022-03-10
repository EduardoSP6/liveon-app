package com.example.liveon_app.repositories;

import com.example.liveon_app.models.User;

import io.realm.Realm;

public class UserRepository {

    Realm realm = Realm.getDefaultInstance();

    public User getAuthenticated() {
        return realm.where(User.class)
                .isNotEmpty("token")
                .findFirst();
    }

    public User create(String username, String fullname, String avatar, String city, String state, String token) {
        try {
            realm.beginTransaction();

            User user = new User();
            user.setUsername(username);
            user.setFullname(fullname);
            user.setAvatar_url(avatar);
            user.setCity(city);
            user.setState_abbr(state);
            user.setToken(token);

            realm.copyToRealm(user);
            realm.commitTransaction();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean destroy(User user) {
        try {
            user.deleteFromRealm();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
