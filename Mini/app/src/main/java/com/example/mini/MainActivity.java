package com.example.mini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mini.Account.accountpg;
import com.example.mini.Model.Messusers;
import com.example.mini.Model.Users;
import com.example.mini.homepage.menuadapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Messusers> listData;
    private RecyclerView rv;
    private menuadapter adapter;

    private RelativeLayout relativelayout;
    private AnimationDrawable animationDrawable;

    Button b1;
    FloatingActionButton b2;
    ImageButton b3,b4;
    TextView t1;


    DatabaseReference myRef;
    FirebaseStorage storage;
    FirebaseAuth auth;
    String userid;
    FirebaseUser user;
    Users users;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        animationDrawable = (AnimationDrawable) relativelayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        listData=new ArrayList<Messusers>();
        //DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        //rv.addItemDecoration(decoration);
        t1=findViewById(R.id.textView);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("customers");
        storage = FirebaseStorage.getInstance();

        users=new Users();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(userid).child("username").getValue().toString();
                t1.setText("Hello "+name+"!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Users");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    Messusers l=npsnapshot.getValue(Messusers.class);
                    listData.add(l);

                }
                adapter=new menuadapter(MainActivity.this,listData);
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });

        SearchView s1=findViewById(R.id.searchView);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, pgsearch.class);
                startActivity(i1);
            }
        });
        b2=findViewById(R.id.fab);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, Orderstatusmain.class);
                startActivity(i1);
            }
        });

        b3=findViewById(R.id.btn_music);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, accountpg.class);
                startActivity(i1);
            }
        });
        b4=findViewById(R.id.btn_fav);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i1);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
