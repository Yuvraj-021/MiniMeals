package com.example.mini;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mini.Model.Menus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menufood extends AppCompatActivity {
    ImageView im;
    TextView t1, t2,t3,t4,t5;
 public Button b1;
FirebaseDatabase fb;
    FirebaseAuth auth;

    ArrayList<Menus>listData;
    private RecyclerView rv;
    private foodmenuadapter adapter;
    String a;
    int b;
    Menus menus;

    public  int[] images= new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4, R.drawable.img5, R.drawable.img6,R.drawable.img7, R.drawable.img8, R.drawable.img9,
            R.drawable.img10, R.drawable.img11, R.drawable.img12,R.drawable.img13, R.drawable.img14, R.drawable.img15,R.drawable.img16, R.drawable.img17, R.drawable.img18,
            R.drawable.img19, R.drawable.img20};

    int spanCount = 3; // 3 columns
    int spacing = 50; // 50px
    boolean includeEdge = true;
    int mMargin=50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodmenu);
        TextView t1=findViewById(R.id.messnme);

        getSupportActionBar().hide();


        rv=(RecyclerView)findViewById(R.id.recyclerview2);
        ImageView img=findViewById(R.id.imageView1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new GridLayoutManager(menufood.this, 2));
        listData=new ArrayList<Menus>();
        rv.addItemDecoration(new SpacesItemDecoration(mMargin));
       // DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //rv.addItemDecoration(decoration);

        a=getIntent().getStringExtra("mname");
        b=getIntent().getIntExtra("imageid",0);

        //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
        t1.setText(a);
        fb = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();
        img.setBackgroundResource(images[b]);
        menus=new Menus();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Menus");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    Menus l=npsnapshot.getValue(Menus.class);
                    String b=l.getMessName();
                    //Toast.makeText(menufood.this, b, Toast.LENGTH_SHORT).show();
                    if(b.equals(a)) {
                        listData.add(l);
                    }
                }
                adapter=new foodmenuadapter(menufood.this,listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
            }
        });
    }

}