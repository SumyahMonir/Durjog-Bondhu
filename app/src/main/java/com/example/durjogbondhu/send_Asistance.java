package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class send_Asistance extends AppCompatActivity {
    Button btnneedhelp,btnaware;
    ImageView inews,imap,iadd,ifriends,ime;
    TextView share1,share2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_asistance);

        btnaware=findViewById(R.id.awareness);
        btnneedhelp=findViewById(R.id.needhelp);
        inews=findViewById(R.id.newsfeed);
        imap=findViewById(R.id.danger);
        iadd=findViewById(R.id.add);
        ifriends=findViewById(R.id.friends);
        ime=findViewById(R.id.myprofile);
        share1=findViewById(R.id.share1);
        share2=findViewById(R.id.share2);

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

        btnaware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),Awareness.class);
                startActivity(chalo);
            }
        });

        btnneedhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1=new Intent(getApplicationContext(),Home_page.class);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.send_assistance), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void shareContent() {
        String shareText = "Check out this amazing content!"; // Replace with your content
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}