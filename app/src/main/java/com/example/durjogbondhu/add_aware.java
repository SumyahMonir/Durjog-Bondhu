package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class add_aware extends AppCompatActivity {
    Button btnaskhelp,Btnsendasis;
    ImageView inews,imap,iadd,ifriends,ime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_aware);


        btnaskhelp= findViewById(R.id.needhelp);
        Btnsendasis=findViewById(R.id.sendassis);
        inews=findViewById(R.id.newsfeed);
        imap=findViewById(R.id.danger);
        iadd=findViewById(R.id.add);
        ifriends=findViewById(R.id.friends);
        ime=findViewById(R.id.myprofile);

        btnaskhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),add.class);
                startActivity(chalo);
            }
        });
        ifriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),Friends.class);
                startActivity(chalo1);
            }
        });
        Btnsendasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),add_sent_assistance.class);
                startActivity(chalo);
            }
        });

        inews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),Home_page.class);
                startActivity(chalo);
            }
        });
        iadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),add.class);
                startActivity(chalo1);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_aware), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}