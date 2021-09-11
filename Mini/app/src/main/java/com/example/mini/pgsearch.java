package com.example.mini;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini.Model.Messusers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class pgsearch extends AppCompatActivity {
    ArrayList<Messusers> list;
    private RecyclerView rv;
    SearchView sv;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpge);

        rv = findViewById(R.id.recyclerview1);
        sv = findViewById(R.id.searchView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(pgsearch.this));

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
    }


    @Override
    public void onStart(){
        super.onStart();
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(Messusers.class));
                        }
                        searchadapter adapterClass = new searchadapter(pgsearch.this,list);
                        rv.setAdapter(adapterClass);
                        rv.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(pgsearch.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (sv != null) {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }


    private void search(String str) {
        ArrayList<Messusers> mylist = new ArrayList<>();
        for (Messusers object : list) {
            if (object.getMessName().contains(str) ||object.getMessType().contains(str) ||object.getMessaddress().contains(str)) {
                mylist.add(object);
            }

        }
        searchadapter adapterClass = new searchadapter(pgsearch.this,mylist);
        rv.setAdapter(adapterClass);
    }
}