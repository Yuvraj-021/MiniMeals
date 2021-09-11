package com.example.mess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mess.Model.MessRequests;
import com.example.mess.Model.Users;
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

public class Recieptpage extends AppCompatActivity{
    DatabaseReference myRef,Ref;
    FirebaseStorage storage;
    FirebaseUser user;
    Users users;
    FirebaseAuth auth,mAuth;
    public long id=0;
    String foodname,messname,address,description,userid,ownername,mobileno;
    Integer price,quantity;
    RadioGroup rg;
    MessRequests messRequests;
    TextView t1,t2,t3,t4;
    Button b1;

    RadioButton radiobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgreciept);

        getSupportActionBar().setTitle("Cart");

        t1=findViewById(R.id.ordername);
        t2=findViewById(R.id.desc);
        t3=findViewById(R.id.qty);
        t4=findViewById(R.id.price);
        b1=findViewById(R.id.btnplc);

        rg=findViewById(R.id.radiogroup);

        foodname=getIntent().getStringExtra("fname");
        price=getIntent().getIntExtra("fprice",0);
        quantity=getIntent().getIntExtra("qty",0);
        description=getIntent().getStringExtra("desc");



        t1.setText(foodname);
        t2.setText(description);
        t3.setText(quantity.toString());
        t4.setText(price.toString());



        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");


        Ref = database.getReference().child("MessRequests");
        messRequests =new MessRequests();

        Ref.addValueEventListener(new ValueEventListener() {
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

                placeord();

                int selectedId = rg.getCheckedRadioButtonId();
                radiobutton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(Recieptpage.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Recieptpage.this,radiobutton.getText(), Toast.LENGTH_SHORT).show();
                }
                String s= (String) radiobutton.getText();
                if(s.equals("PAY USING UPI"))
                {
                    Intent i1=new Intent(Recieptpage.this,paymentpg.class);
                    i1.putExtra("messname",messname);
                    i1.putExtra("total",price);
                    startActivity(i1);
               }

            }
        });
    }

    public void placeord() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    address = dataSnapshot.child(userid).child("messaddress").getValue().toString();
                    mobileno = dataSnapshot.child(userid).child("mobileno").getValue().toString();
                    ownername = dataSnapshot.child(userid).child("ownerName").getValue().toString();
                    messname = dataSnapshot.child(userid).child("messName").getValue().toString();

                    messRequests.setFoodName(foodname);
                    messRequests.setDescription(description);
                    messRequests.setInprice(price);
                    messRequests.setQuantity(quantity);
                    messRequests.setUserid(user.getUid());
                    messRequests.setMessName(messname);
                    messRequests.setAddress(address);
                    messRequests.setMobileno(mobileno);
                    messRequests.setOwername(ownername);

                    Ref.child(String.valueOf(id + 1)).setValue(messRequests);
                    Toast.makeText(getApplicationContext(), "Order Placed Successully", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Recieptpage.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }
}






