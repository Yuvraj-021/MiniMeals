package com.example.minimeals.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimeals.AdapterStatus;
import com.example.minimeals.R;
import com.example.minimeals.adaptermenu;
import com.example.minimeals.model.OrderStatus;
import com.example.minimeals.model.User1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ArrayList<OrderStatus> listData;
    private RecyclerView rv;
    private AdapterStatus adapter;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
String a;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        super.onCreate(savedInstanceState);
        rv = root.findViewById(R.id.reclycler_view_notifi);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData = new ArrayList<OrderStatus>();
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);
        firebaseAuth = FirebaseAuth.getInstance();
firebaseUser=firebaseAuth.getCurrentUser();
a=firebaseUser.getEmail();
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Orderstatus");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        OrderStatus l = npsnapshot.getValue(OrderStatus.class);
                       if(l.getEmail().contains(a.toString())){
                        listData.add(l);
                    }
                }
                adapter = new AdapterStatus(getActivity(), listData);
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