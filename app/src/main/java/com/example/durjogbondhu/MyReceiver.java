package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if ("com.example.broadcast.TEXT_ACTION".equals(action)) {
            String paisi = intent.getStringExtra("text_key");
            if (paisi != null) {
                Log.i("msg", "Message Displayed: " + paisi);
                Toast.makeText(context, paisi, Toast.LENGTH_LONG).show();
            }
        }
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

            if (isAirplaneModeOn) {
                Log.i("msg", "Airplane Mode is On");
                Toast.makeText(context, "Airplane Mode is On", Toast.LENGTH_LONG).show();
            } else {
                Log.i("msg", "Airplane Mode is Off");
                Toast.makeText(context, "Airplane Mode is Off", Toast.LENGTH_LONG).show();
            }
        }
        else if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
            int state= intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
            if(state==BluetoothAdapter.STATE_TURNING_OFF){
                Log.i("msg", "BLuetooth is Turning Off");
                Toast.makeText(context,"Bluetooth is Turning Off",Toast.LENGTH_LONG).show();
            }
            else if(state==BluetoothAdapter.STATE_TURNING_ON){
                Log.i("msg", "BLuetooth is Turning On");
                Toast.makeText(context,"Bluetooth is Turning On",Toast.LENGTH_LONG).show();
            }
        }
        else if(Intent.ACTION_BATTERY_LOW.equals(action))
        {
            int level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

            float battperc= (level/(float)scale)*100;


            boolean isBatteryLow = battperc < 20;
            if(isBatteryLow){
                Log.i("msg", "Battery is low");
                Toast.makeText(context, "Battery is Low!!", Toast.LENGTH_LONG).show();
            }
            else {
                Log.i("msg", "Battery is above 20");
                Toast.makeText(context, "Battery is above 20% :)", Toast.LENGTH_LONG).show();
            }
        }
        else if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)){
            int state= intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);
            if(state==WifiManager.WIFI_STATE_ENABLED){
                Log.i("msg", "Wifi is enabled");
                Toast.makeText(context, "Wifi is enabled", Toast.LENGTH_LONG).show();
            }
            else if (state==WifiManager.WIFI_STATE_DISABLED){
                Log.i("msg", "Wifi is disabled");
                Toast.makeText(context, "Wifi is disabled", Toast.LENGTH_LONG).show();
            }
        }
        else if(Intent.ACTION_POWER_CONNECTED.equals(action)){
            Log.i("msg", "Power is connected");
            Toast.makeText(context, "Power is Connected!", Toast.LENGTH_LONG).show();
        }
        else if(Intent.ACTION_POWER_DISCONNECTED.equals(action)){
            Log.i("msg", "Power is disconnected");
            Toast.makeText(context, "Power is Disconnected!", Toast.LENGTH_LONG).show();
        }
    }
}
