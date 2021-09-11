package com.example.mess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mess.Model.Menus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewMenu extends AppCompatActivity {
     ArrayList<Menus>listData;
    private RecyclerView rv;
    private adaptermenu adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuviewor);

        rv=(RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(viewMenu.this));
        listData=new ArrayList<Menus>();
      //  DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
       // rv.addItemDecoration(decoration);

        final  FirebaseUser user;
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
       user=auth.getCurrentUser();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Menus");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                       String userid=user.getUid();
                       Menus menus=new Menus();
                        Menus l=npsnapshot.getValue(Menus.class);
                        String a=l.getUserid();
                        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
                        if(userid.equals(a)) {
                            listData.add(l);
                        }
                    }
                    adapter=new adaptermenu(viewMenu.this,listData);
                    rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
