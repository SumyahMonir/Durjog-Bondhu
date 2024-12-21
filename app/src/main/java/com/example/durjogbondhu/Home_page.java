package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        callFragment(new NewsFeed(), 0);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.page_1) {
                        callFragment(new NewsFeed(), 1);
                        return true;}
                else if (item.getItemId()==R.id.page_2){
                    callFragment(new MapFragment(), 1);
                        return true;}
                else if (item.getItemId()==R.id.page_3){
                    callFragment(new AddpostFragment(), 1);
                    return true;
                }
                else
                {return false;
                }
            }
        });


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.page_1) {
                    // Handle the selection of page_1
                    callFragment(new NewsFeed(), 1);
                    }
                else if (item.getItemId()==R.id.page_2){
                    callFragment(new MapFragment(), 1);
                    }
                else if (item.getItemId()==R.id.page_3){
                    callFragment(new AddpostFragment(), 1);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homepage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void callFragment (Fragment fragment, int Status){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(Status == 0){
            fragmentTransaction.add(R.id.home_container,fragment);
        } else if (Status == 1) {
            fragmentTransaction.replace(R.id.home_container,fragment);
        }
        fragmentTransaction.commit();
    }
}
