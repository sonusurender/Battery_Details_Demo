package com.batterydetails_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by SONU on 23/05/16.
 */
public class Battery_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //Get intent data of battery details
        boolean isPresent = intent.getBooleanExtra("present", false);
        String technology = intent.getStringExtra("technology");
        int plugged = intent.getIntExtra("plugged", -1);
        int scale = intent.getIntExtra("scale", -1);
        int health = intent.getIntExtra("health", 0);
        int status = intent.getIntExtra("status", 0);
        int rawlevel = intent.getIntExtra("level", -1);
        int voltage = intent.getIntExtra("voltage", 0);
        int temperature = intent.getIntExtra("temperature", 0);
        int level = 0;

        Bundle bundle = intent.getExtras();
        Log.i("BatteryLevel", bundle.toString());
        if (isPresent) {
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }

            //Concatenate all details of battery
            String info = "Battery Level: " + level + "%\n";
            info += ("Technology: " + technology + "\n");
            info += ("Plugged: " + getPlugTypeString(plugged) + "\n");
            info += ("Health: " + getHealthString(health) + "\n");
            info += ("Status: " + getStatusString(status) + "\n");
            info += ("Voltage: " + voltage + "\n");
            info += ("Temperature: " + temperature + "\n");
            new MainActivity().setBatteryLevelText(info + "\n\nBundle Data:\n" + bundle.toString());//Set battery text in main Activity
        } else
            new MainActivity().setBatteryLevelText("Battery not present!!!");// If no battery exist display this message

    }

    //Get Plug type for battery
    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
        }
        return plugType;
    }

    //Get Health of battery
    private String getHealthString(int health) {
        String healthString = "Unknown";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
        }

        return healthString;
    }

    //Get Status of battery
    private String getStatusString(int status) {
        String statusString = "Unknown";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }

        return statusString;
    }
}
