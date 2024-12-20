package com.example.durjogbondhu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    private Button btnSignIn, btnLogIn;
    private EditText inputPassword, inputNumber;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnSignIn = findViewById(R.id.signin);
        btnLogIn = findViewById(R.id.login);
        inputNumber = findViewById(R.id.enterNumber);
        inputPassword = findViewById(R.id.enterPassword);
        progressBar = findViewById(R.id.progress1);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), sign_in.class);
            startActivity(intent);
        });

        btnLogIn.setOnClickListener(view -> logInUser());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void logInUser() {
        String email = inputNumber.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter your password!", Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(log_in.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("flag",true);
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), Home_page.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                            Toast.makeText(log_in.this, "Authentication failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
