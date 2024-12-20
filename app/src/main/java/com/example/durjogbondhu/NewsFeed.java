package com.example.durjogbondhu;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NewsFeed extends Fragment {

    private Button btnAware, Btnsendasis, btnneedhelp;  // Declare button variables

    public NewsFeed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        // Initialize the buttons
        btnAware = view.findViewById(R.id.awareness);
        Btnsendasis = view.findViewById(R.id.sendassis);
        btnneedhelp = view.findViewById(R.id.needhelp);

        // Set click listeners for buttons
        btnAware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new awarenessy(), 1);
                btnAware.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                btnneedhelp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
                Btnsendasis.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
            }
        });

        Btnsendasis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new Send_Assistance(), 1);
                Btnsendasis.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                btnneedhelp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
                btnAware.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
            }
        });

        btnneedhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new need_help(), 1);
                Btnsendasis.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
                btnneedhelp.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                btnAware.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.d7d5d5)));
            }
        });

        // Default fragment
        callFragment(new need_help(), 0);

        return view;
    }

    public void callFragment(Fragment fragment, int Status) {
        FragmentManager fragmentManager = getParentFragmentManager();  // Use getParentFragmentManager() instead of getSupportFragmentManager()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (Status == 0) {
            fragmentTransaction.add(R.id.container, fragment);
        } else if (Status == 1) {
            fragmentTransaction.replace(R.id.container, fragment);
        }
        fragmentTransaction.commit();
    }
}
