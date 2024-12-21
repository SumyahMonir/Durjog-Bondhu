package com.example.durjogbondhu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {
    TextView viewname,veiwemail,viewphone;
    EditText chngname,chngphone;
    Button BtnOut,Deleteac,updatename,updatephone;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fstore;
    String userID;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        BtnOut = view.findViewById(R.id.Logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        viewname = view.findViewById(R.id.viewname);
        veiwemail = view.findViewById(R.id.veiwemail);
        viewphone = view.findViewById(R.id.viewphone);
        fstore = FirebaseFirestore.getInstance();
        userID = auth.getCurrentUser().getUid();
        chngname = view.findViewById(R.id.chngname);
        chngphone = view.findViewById(R.id.chngphone);
        updatename = view.findViewById(R.id.updatename);
        updatephone = view.findViewById(R.id.updatephone);
        Deleteac = view.findViewById(R.id.Delectac);

        if (user == null) {
            Intent Jhaa = new Intent(getContext(), sign_in.class);
            startActivity(Jhaa);
            getActivity().finish();
        } else {
            DocumentReference documentReference = fstore.collection("users").document(userID);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value != null) {
                        viewname.setText("Name: " + value.getString("Name"));
                        veiwemail.setText("Email: " + value.getString("email"));
                        viewphone.setText("Phone Number: " + value.getString("Phone"));
                    }
                }
            });

            updatename.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String chName = chngname.getText().toString();
                    if (!chName.isEmpty()) {
                        documentReference.update("Name", chName);
                    }
                }
            });

            updatephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String chPhone = chngphone.getText().toString();
                    if (!chPhone.isEmpty()) {
                        documentReference.update("Phone", chPhone);
                    }
                }
            });

            Deleteac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    documentReference.delete();
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "Account deleted successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), sign_in.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    } else {
                                        Toast.makeText(getContext(), "Account deletion failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }

        BtnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Logging Out!", Toast.LENGTH_LONG).show();

                Intent Byebye = new Intent(getContext(), sign_in.class);
                startActivity(Byebye);
                getActivity().finish();
            }
        });

        return view;
    }
}