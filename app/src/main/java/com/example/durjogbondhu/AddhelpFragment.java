package com.example.durjogbondhu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AddhelpFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore fstore;
    private String userID;

    public AddhelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addhelp, container, false);
        TextView viewname = view.findViewById(R.id.UserName);

        // Initialize Firebase instances
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        // Get the current user
        user = auth.getCurrentUser();
        if (user == null) {
            viewname.setText("No user logged in");
            return view; // Exit early if no user is logged in
        }

        // Retrieve userID
        userID = user.getUid();

        // Set up Firestore listener
        DocumentReference documentReference = fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null && value.exists()) {
                    String name = value.getString("Name");
                    viewname.setText(name);
                }
            }
        });

        return view;
    }
}
