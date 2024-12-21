package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {
    ImageView inews,imap,iadd,inew,ime;
    Spinner listspin;
    ListView listitem;

    ArrayList<String> spinvalues=new ArrayList<>();
    ArrayList<String> listvalues=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friends);


        inews=findViewById(R.id.newsfeed);
        imap=findViewById(R.id.danger);
        iadd=findViewById(R.id.add);
        inew=findViewById(R.id.newsfeed);
        ime=findViewById(R.id.myprofile);

        listspin=findViewById(R.id.spin);
        spinvalues.add("Sort by");
        spinvalues.add("Last Chatted");
        spinvalues.add("Last Online");
        listitem = findViewById(R.id.list);
        listvalues.add("Rahul");
        listvalues.add("Aarif");
        listvalues.add("Sajib");
        listvalues.add("Imran");
        listvalues.add("Farhan");
        listvalues.add("Rifat");

        ArrayAdapter<String> spinadapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,spinvalues);
        listspin.setAdapter(spinadapter);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listvalues);
        listitem.setAdapter(adapter);

//        inews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent chalo=new Intent(getApplicationContext(),Home_page.class);
//                startActivity(chalo);
//            }
//        });
//        iadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent chalo1=new Intent(getApplicationContext(),add.class);
//                startActivity(chalo1);
//            }
//        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.friends), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}