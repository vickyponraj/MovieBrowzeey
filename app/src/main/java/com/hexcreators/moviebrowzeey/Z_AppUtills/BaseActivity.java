package com.hexcreators.moviebrowzeey.Z_AppUtills;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {

    private ConnectivityChangeReceiver connectivityChangeReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityChangeReceiver = new ConnectivityChangeReceiver();
            intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            this.registerReceiver(connectivityChangeReceiver, intentFilter);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (connectivityChangeReceiver != null) {
            this.unregisterReceiver(connectivityChangeReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (connectivityChangeReceiver != null) {
            this.registerReceiver(connectivityChangeReceiver, intentFilter);
        }
    }
}
