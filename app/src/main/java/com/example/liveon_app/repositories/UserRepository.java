package com.example.liveon_app.repositories;

import com.example.liveon_app.models.User;

import io.realm.Realm;

public class UserRepository {

    public User getAuthenticated() {
        Realm realm = Realm.getDefaultInstance();

        User user = realm.where(User.class)
                .isNotEmpty("token")
                .findFirst();

        realm.close();
        return user;
    }

    public User create(String username, String fullname, String avatar, String city, String state, String token) {
        Realm realm = Realm.getDefaultInstance();
        try {
            User user = new User();
            user.setUsername(username);
            user.setFullname(fullname);
            user.setAvatar_url(avatar);
            user.setCity(city);
            user.setState_abbr(state);
            user.setToken(token);

            return user;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }
        return null;
    }

    public boolean destroy(User user) {
        Realm realm = Realm.getDefaultInstance();
        try {
            user.deleteFromRealm();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            realm.close();
        }
        return false;
    }
}
