package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Home_page extends AppCompatActivity {
    Button btnAware,Btnsendasis,btnneedhelp;
    ImageView inews,imap,iadd,ifriends,ime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        btnAware= findViewById(R.id.awareness);
        Btnsendasis=findViewById(R.id.sendassis);
        btnneedhelp=findViewById(R.id.needhelp);
        inews=findViewById(R.id.newsfeed);
        imap=findViewById(R.id.danger);
        iadd=findViewById(R.id.add);
        ifriends=findViewById(R.id.friends);
        ime=findViewById(R.id.myprofile);


        btnAware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),Awareness.class);
                startActivity(chalo);
            }
        });
        callFragment(new need_help(),0);

        Btnsendasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new Send_Assistance(),1);
            }
        });
        btnneedhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new need_help(),1);
            }
            });

        iadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),add.class);
                startActivity(chalo1);
            }
        });
        ifriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),Friends.class);
                startActivity(chalo1);
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
            fragmentTransaction.add(R.id.container,fragment);
        } else if (Status == 1) {
            fragmentTransaction.replace(R.id.container,fragment);
        }
        fragmentTransaction.commit();
    }
}
