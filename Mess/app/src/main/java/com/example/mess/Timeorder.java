package com.example.mess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mess.Model.Menus;
import com.example.mess.Model.Orderstatus;
import com.example.mess.Model.Requests;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Timeorder extends AppCompatActivity {

  EditText e1;
    String Foodname,time,Customername,MessName,status,email;
    Integer price,qty;
    Button b1;
    long id=0;

    DatabaseReference myRef, demoRef;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser user;
    Orderstatus orderstatus;
    FirebaseAuth auth;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordertime);

        Foodname=getIntent().getStringExtra("fname");
        Customername=getIntent().getStringExtra("custname");
        price=getIntent().getIntExtra("fprice",0);
        qty=getIntent().getIntExtra("fqty",0);
        MessName=getIntent().getStringExtra("messname");
        email=getIntent().getStringExtra("email");



        e1 = findViewById(R.id.editText11);
        b1 =findViewById(R.id.button5);
        orderstatus=new Orderstatus();
        Toast.makeText(Timeorder.this,email,Toast.LENGTH_SHORT).show();
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Orderstatus");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 status="Accepted";
                 time=e1.getText().toString();
                 orderstatus.setFname(Foodname);
                 orderstatus.setCustomerName(Customername);
                 orderstatus.setEmail(email);
                 orderstatus.setTotal(price);
                 orderstatus.setQuantity(qty);
                 orderstatus.setTime(time);
                 orderstatus.setStatus(status);
                 orderstatus.setMessname(MessName);
                 myRef.child(String.valueOf(id+1)).setValue(orderstatus);
                 Toast.makeText(getApplicationContext(),"Order Accepted Successully",Toast.LENGTH_SHORT).show();


                 removeval();

             }
         });


    }
    public void removeval()
    {
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Requests");
        nm.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    Requests requests=new Requests();
                   Requests l=npsnapshot.getValue(Requests.class);
                    String a=l.getCustomerName();
                    String b=l.getFoodName();
                    if(a.equals(Customername) && b.equals(Foodname))
                    {
                        npsnapshot.getRef().removeValue();
                    }

               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

}
