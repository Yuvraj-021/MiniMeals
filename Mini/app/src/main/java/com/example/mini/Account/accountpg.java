package com.example.mini.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mini.MainActivity;
import com.example.mini.Model.Users;
import com.example.mini.R;
import com.example.mini.aboutuspg;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class accountpg extends AppCompatActivity {
    private TextView t1,t2,t3,t4,t5;
    DatabaseReference myRef;
    FirebaseStorage storage;

    FirebaseUser user;
    Users users;
    FirebaseAuth auth;
    RelativeLayout rl,r2;
    String a,b,c,d,e,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pgaccount);
        t2=(TextView)findViewById(R.id.acno);
        t3=(TextView)findViewById(R.id.acemail);
        t4=(TextView)findViewById(R.id.acaddress);
        t5=(TextView)findViewById(R.id.acprofile);
        t1=findViewById(R.id.bktohm);
        rl=findViewById(R.id.rlabout);
        r2=findViewById(R.id.rllogout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(accountpg.this, aboutuspg.class);
                startActivity(i1);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(accountpg.this, MainActivity.class);
                startActivity(i1);
            }
        });
        t1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(accountpg.this,Loginpg.class);
                startActivity(intent);
            }
        });
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
                String address=dataSnapshot.child(userid).child("address").getValue().toString();

                String email=dataSnapshot.child(userid).child("email").getValue().toString();
                String no=dataSnapshot.child(userid).child("mobileno").getValue().toString();

                t5.setText(name);
                t4.setText(address);
                t2.setText(no);
                t3.setText(email);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(accountpg.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });




        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

    }
}
