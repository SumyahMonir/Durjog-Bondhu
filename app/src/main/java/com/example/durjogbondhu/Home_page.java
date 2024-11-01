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

public class Home_page extends AppCompatActivity {
    Button btnAware,Btnsendasis;
    ImageView inews,imap,iadd,ifriends,ime;
    TextView share1,share2,share3,share4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        btnAware= findViewById(R.id.awareness);
        Btnsendasis=findViewById(R.id.sendassis);
        inews=findViewById(R.id.newsfeed);
        imap=findViewById(R.id.danger);
        iadd=findViewById(R.id.add);
        ifriends=findViewById(R.id.friends);
        ime=findViewById(R.id.myprofile);
        share1=findViewById(R.id.share1);
        share2=findViewById(R.id.share2);
        share3=findViewById(R.id.share3);
        share4=findViewById(R.id.share4);

        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent();
            }
        });
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent();
            }
        });
        share3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent();
            }
        });
        share4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareContent();
            }
        });

        btnAware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),Awareness.class);
                startActivity(chalo);
            }
        });

        Btnsendasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),send_Asistance.class);
                startActivity(chalo1);
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

    private void shareContent() {
        String shareText = "Check out this amazing content!";
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
