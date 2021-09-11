package com.example.minimeals;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.example.minimeals.model.Menus;
import java.util.ArrayList;

public class mess_order extends AppCompatActivity {
    ImageView im;
    TextView t1, t2,t3,t4,t5;
 public Button b1;
FirebaseDatabase fb;
    FirebaseAuth auth;
    ArrayList<Menus>listData;
    private RecyclerView rv;
    private menuadapter adapter;
    String a,b,c,d,e,userid,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_info);

        rv=(RecyclerView)findViewById(R.id.recycler_view1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(mess_order.this));
        listData=new ArrayList<Menus>();
        DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);

        Bundle bundle = getIntent().getExtras();
        a = bundle.getString("messname");
        b=bundle.getString("address");
        im = findViewById(R.id.foid);
        t1 = findViewById(R.id.food);
        t2 = findViewById(R.id.price);
        t3 = findViewById(R.id.msnm);
        t4=findViewById(R.id.maddress);
        b1 = findViewById(R.id.add);
        t5=findViewById(R.id.bktohm);
        fb = FirebaseDatabase.getInstance();
     t3.setText(a);
     t4.setText(b);
        auth = FirebaseAuth.getInstance();
        t5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(mess_order.this,home_activity.class);
                startActivity(intent);
            }
        });
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Menus");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    Menus l=npsnapshot.getValue(Menus.class);
                   if(l.getMessName().contains(a.toString())) {
                       listData.add(l);
                   }
                }
              adapter=new menuadapter(mess_order.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });
    }

}