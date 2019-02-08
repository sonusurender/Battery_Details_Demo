package com.batterydetails_demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static TextView battery_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        battery_details = (TextView) findViewById(R.id.battery_details);
        registerBatteryLevelReceiver();
    }

    //set up toolbar
    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Register Battery receiver with intent filter
    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new Battery_Receiver(), filter);
    }

    //Set text of battery details
    public void setBatteryLevelText(String text) {
        battery_details.setText(text);
    }

}
