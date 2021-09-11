package com.example.mess.ui.dashboard;

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

import com.example.mess.Model.Requests;
import com.example.mess.Model.Users;
import com.example.mess.R;
import com.example.mess.adapterorder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    ArrayList<Requests>listData;
    private RecyclerView rv;
    private adapterorder adapter;

    FirebaseUser user;
    Users users;
    FirebaseAuth auth;
    String userid;
    String a;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rv=(RecyclerView)root.findViewById(R.id.recycler_view1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        listData=new ArrayList<Requests>();

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        //DividerItemDecoration decoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        //rv.addItemDecoration(decoration);


        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    a=dataSnapshot.child(userid).child("messName").getValue().toString();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });


        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Requests");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){

                    Requests l=npsnapshot.getValue(Requests.class);
                    String b=l.getMessName();
                    if(b.equals(a))
                        listData.add(l);
                }
                adapter=new adapterorder(getActivity(),listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}