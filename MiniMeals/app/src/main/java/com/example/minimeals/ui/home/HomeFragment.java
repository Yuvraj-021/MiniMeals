package com.example.minimeals.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.minimeals.R;
import com.example.minimeals.adaptermenu;
import com.example.minimeals.model.User1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<User1> listData;
    private RecyclerView rv;
    private adaptermenu adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.menuviewor_for_home, container, false);
        super.onCreate(savedInstanceState);

        rv = root.findViewById(R.id.reclycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData = new ArrayList<User1>();
      //  DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
       // rv.addItemDecoration(decoration);


        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Users");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    User1 l = npsnapshot.getValue(User1.class);
                    listData.add(l);
                }
                adapter = new adaptermenu(getActivity(), listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "opps.....", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

}