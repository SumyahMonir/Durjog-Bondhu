package com.example.durjogbondhu;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SetPassword extends AppCompatActivity {
    Button btnlogin;
    EditText pass1,pass2;
    String userID;
    String name,phoneno,email;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_password);

        pass1=findViewById(R.id.enterPassword);
        pass2=findViewById(R.id.enterPassword2);
        btnlogin=findViewById(R.id.login);
        String Name = getIntent().getStringExtra("name");
        String Number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass1.getText().toString();
                String password2 = pass2.getText().toString();

                if (!password.equals(password2)) {
                    Toast.makeText(getApplicationContext(),"Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                if(password2.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                                    Toast.makeText(SetPassword.this, "Registration Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fstore.collection("users").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Name",Name);
                                    user.put("Phone",Number);
                                    user.put("email",email);
                                    documentReference.set(user);
                                    Intent letsgo = new Intent(getApplicationContext(), Home_page.class);
                                    startActivity(letsgo);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SetPassword.this, "Registration failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });}

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}