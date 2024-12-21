package com.example.durjogbondhu;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class log_in extends AppCompatActivity {
    Button btnSignIn, btnLogIn;
    EditText inputPassword, inputNumber;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar; // Declare ProgressBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        // Initialize views
        btnSignIn = findViewById(R.id.signin);
        btnLogIn = findViewById(R.id.login);
        inputNumber = findViewById(R.id.enterNumber);
        inputPassword = findViewById(R.id.enterPassword);
        progressBar = findViewById(R.id.progress1);  // Initialize ProgressBar

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        // SignUp button click
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chalo1 = new Intent(getApplicationContext(), sign_in.class);
                startActivity(chalo1);
            }
        });

        // Login button click
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = inputNumber.getText().toString();
                String password = inputPassword.getText().toString();

                // Validate input fields
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Email!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Password!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // Sign in with email and password
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Toast.makeText(log_in.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("flag", true);
                                    editor.apply();

                                    // Navigate to Home page
                                    Intent letsgo = new Intent(getApplicationContext(), Home_page.class);
                                    startActivity(letsgo);
                                    finish();
                                } else {
                                    // Authentication failed
                                    Toast.makeText(log_in.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Set window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
