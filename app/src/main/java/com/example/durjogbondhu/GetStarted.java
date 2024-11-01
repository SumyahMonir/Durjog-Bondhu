package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GetStarted extends AppCompatActivity {
    Button btnGetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_started);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        MyReceiver objReceiver = new MyReceiver();
        registerReceiver(objReceiver,intentFilter);

        btnGetStarted=findViewById(R.id.getstarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo = new Intent(getApplicationContext(),sign_in.class);
                startActivity(chalo);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("state", "onStart() Method is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("state", "onResume() Method is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("state", "onPause() Method is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("state", "onDestroy() Method is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("state", "onStop() Method is called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("state", "onRestart() Method is called");
    }

}