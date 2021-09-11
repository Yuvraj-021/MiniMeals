package com.example.mess;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mess.Model.MessRequests;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class reqfood extends AppCompatActivity {
    private Button b1;
    private EditText ed1, ed2,ed3,ed4;
    DatabaseReference myRef,Ref;
    FirebaseUser user;
    MessRequests messRequests;
    FirebaseAuth auth;
    private Intent data;
    long id=0;
    String a,d,userid;
    int b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodreq);

        getSupportActionBar().setTitle("Request Food Items");
        ed1 = findViewById(R.id.foodname);
        ed2 = findViewById(R.id.fooddescription);
        ed3=findViewById(R.id.qty);
        ed4=findViewById(R.id.inprice);


        b1 =findViewById(R.id.submit);


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("MessRequests");
        messRequests =new MessRequests();



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

                uploadreq();

            }
        });
    }



    private void uploadreq() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Ref = database.getReference("Users");
        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    String name=dataSnapshot.child(userid).child("messName").getValue().toString();
                    String address=dataSnapshot.child(userid).child("messaddress").getValue().toString();
                    String ownername=dataSnapshot.child(userid).child("ownerName").getValue().toString();
                    String no=dataSnapshot.child(userid).child("mobileno").getValue().toString();

                    a=ed1.getText().toString().trim();
                    d=ed2.getText().toString().trim();
                    b=Integer.parseInt(ed3.getText().toString().trim());
                    c = Integer.parseInt(ed4.getText().toString().trim());
                    messRequests.setFoodName(a);
                    messRequests.setDescription(d);
                    messRequests.setInprice(c);
                    messRequests.setQuantity(b);
                    messRequests.setUserid(user.getUid());
                    messRequests.setMessName(name);
                    messRequests.setAddress(address);
                    messRequests.setMobileno(no);
                    messRequests.setOwername(ownername);

                    myRef.child(String.valueOf(id+1)).setValue(messRequests);
                    Toast.makeText(getApplicationContext(),"Data Inserted Successully",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

}

