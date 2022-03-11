package com.example.liveon_app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LiveOnApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Realm configuration
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("liveondb.realm")
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
