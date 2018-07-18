package com.hexcreators.moviebrowzeey;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.hexcreators.moviebrowzeey.Z_AppUtills.ConnectivityChangeReceiver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieBrowzeey extends MultiDexApplication {

    private static MovieBrowzeey instance;

    public static synchronized MovieBrowzeey getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        instance = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("movieBrowzeey.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

    }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }

    public void setConnectivityListener(ConnectivityChangeReceiver.ConnectivityReceiverListener listener) {
        ConnectivityChangeReceiver.connectivityReceiverListener = listener;
    }
}
