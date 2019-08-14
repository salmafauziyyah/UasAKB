package com.salmafauziyyah.uasakb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
//8-08-2019
//10116596
//Salma Fauziyyah
//AKB3
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("mahasiswa.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
