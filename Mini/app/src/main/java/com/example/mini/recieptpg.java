package com.example.mini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mini.Model.Requests;
import com.example.mini.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class recieptpg extends AppCompatActivity {
    Button plus,minus,order;
    TextView foodname,count,price,total,messname,description;
    int i,c,d,pri,tot;
    String a,b,e,userid,address,mobileno,email,customername;
    DatabaseReference myRef;
    FirebaseStorage storage;
    FirebaseUser user;
    Users users;
    FirebaseAuth auth,mAuth;
    public long id=0;
String msname,fdname,pr,desc;
RadioButton radiobutton;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciept);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        Bundle bundle = getIntent().getExtras();
        msname = bundle.getString("messname");
       fdname=bundle.getString("foodname");
      pr=bundle.getString("price");
      desc=bundle.getString("description");

        minus=findViewById(R.id.rminus);
        plus =findViewById(R.id.rplus);
        order=findViewById(R.id.rorder);
        messname=findViewById(R.id.rmsnm);
        foodname=findViewById(R.id.rfnm);
        description=findViewById(R.id.descr_r);
        count=findViewById(R.id.rcount);
        price=findViewById(R.id.rprice);
        total=findViewById(R.id.subprice);
        rg=findViewById(R.id.radiogroup);

        i=Integer.parseInt(count.getText()+"");
       pri=Integer.parseInt(pr);
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        email=user.getEmail();
       // customername=user.getDisplayName();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("customers");
        storage = FirebaseStorage.getInstance();

        users=new Users();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                address=dataSnapshot.child(userid).child("address").getValue().toString();
                mobileno=dataSnapshot.child(userid).child("mobileno").getValue().toString();
                customername=dataSnapshot.child(userid).child("username").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(recieptpg.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

messname.setText(msname);
foodname.setText(fdname);
price.setText(pr);
description.setText(desc);
        tot=i*pri;
        total.setText(tot+"");
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase databaser= FirebaseDatabase.getInstance();
        final DatabaseReference ref=databaser.getReference("Requests");
        ref.addValueEventListener(new ValueEventListener() {
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
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(i>1){ i=i-1;
                   count.setText(i+"");
                   tot=i*pri;
                   total.setText(tot+"");
                           }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(i<10){
                   i=i+1;
                   count.setText(i+"");
                   tot=i*pri;
                   total.setText(tot+"");
            }
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=messname.getText().toString().trim();
               b=foodname.getText().toString().trim();
                c=Integer.parseInt(count.getText().toString().trim());
                d=Integer.parseInt(total.getText().toString().trim());

                Requests user = new Requests(a,b,c,d,email,address,mobileno,customername);
                FirebaseDatabase.getInstance().getReference("Requests")
                        .child(String.valueOf(id+1))
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                          //  Toast.makeText(reciept.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                        } else {
                                        }
                    }
                });

                Toast.makeText(getApplicationContext(),"Your order is placed",Toast.LENGTH_SHORT).show();

                int selectedId = rg.getCheckedRadioButtonId();
                radiobutton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(recieptpg.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(recieptpg.this,radiobutton.getText(), Toast.LENGTH_SHORT).show();
                }

                if(radiobutton.getText().equals("PAY USING UPI"))
                {
                    Intent i1=new Intent(recieptpg.this,paymentpg.class);
                    i1.putExtra("messname",a);
                    i1.putExtra("total",d);
                    startActivity(i1);
                }

            }

        });

    }

}