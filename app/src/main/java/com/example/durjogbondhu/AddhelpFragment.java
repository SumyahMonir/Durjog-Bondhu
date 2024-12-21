package com.example.durjogbondhu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView imageView,imagev;
    ImageView btn,btn2;
    private final int Request_Image_Capture=1;
    private final int Request_Gallery_Capture=2;

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
        imageView = view.findViewById(R.id.upimage);
        btn = view.findViewById(R.id.fromcamera);
        btn2 = view.findViewById(R.id.fromgalley);
        imagev = view.findViewById(R.id.imageView12);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,Request_Image_Capture);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,Request_Gallery_Capture);
            }
        });

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK){
            if(requestCode == Request_Image_Capture){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(image);
                imagev.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }
        }
        if (resultCode == getActivity().RESULT_OK){
            if(requestCode == Request_Gallery_Capture){
                imageView.setImageURI(data.getData());
                imagev.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
