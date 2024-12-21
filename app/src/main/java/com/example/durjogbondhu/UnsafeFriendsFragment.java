package com.example.durjogbondhu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class UnsafeFriendsFragment extends Fragment {
    Spinner listspin;
    ListView listitem;
    ArrayList<String> spinvalues=new ArrayList<>();
    ArrayList<String> listvalues=new ArrayList<>();

    public UnsafeFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unsafe_friends, container, false);

        listspin=view.findViewById(R.id.spin);
        spinvalues.add("Sort by");
        spinvalues.add("Last Chatted");
        spinvalues.add("Last Online");
        listitem = view.findViewById(R.id.list);
        listvalues.add("Rifah");
        listvalues.add("Aaman");
        listvalues.add("Sumayah");
        listvalues.add("Saadman");
        listvalues.add("Farhan");
        listvalues.add("Rayhan");

        ArrayAdapter<String> spinadapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,spinvalues);
        listspin.setAdapter(spinadapter);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listvalues);
        listitem.setAdapter(adapter);
        return view;
    }
}