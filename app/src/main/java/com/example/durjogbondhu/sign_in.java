package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sign_in extends AppCompatActivity {
    Button btnSignIn,btnLogIn;
    EditText inputName,inputNumber,inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);


        btnSignIn=findViewById(R.id.signin);
        btnLogIn=findViewById(R.id.login);
        inputNumber=findViewById(R.id.editNumber);
        inputName=findViewById(R.id.editname);
        inputEmail = findViewById(R.id.editEmail);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo=new Intent(getApplicationContext(),log_in.class);
                startActivity(chalo);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent chalo1=new Intent(getApplicationContext(),OTP.class);
                chalo1.putExtra("name",inputName.getText().toString());
                chalo1.putExtra("number",inputNumber.getText().toString());
                chalo1.putExtra("email",inputEmail.getText().toString());

                startActivity(chalo1);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}