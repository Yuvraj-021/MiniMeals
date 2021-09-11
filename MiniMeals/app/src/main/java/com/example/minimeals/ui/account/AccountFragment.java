package com.example.minimeals.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.minimeals.MainActivity;
import com.example.minimeals.aboutuspg;
import com.example.minimeals.model.Users;
import com.example.minimeals.R;
import com.example.minimeals.home_activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class AccountFragment extends Fragment {
    private TextView t1,t2,t3,t4,t5;
    DatabaseReference myRef;
    FirebaseStorage storage;

    FirebaseUser user;
    Users users;
    FirebaseAuth auth;
    RelativeLayout rl,r2;
    String a,b,c,d,e,userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        t2=(TextView)root.findViewById(R.id.acno);
        t3=(TextView)root.findViewById(R.id.acemail);
        t4=(TextView)root.findViewById(R.id.acaddress);
        t5=(TextView)root.findViewById(R.id.acprofile);
t1=root.findViewById(R.id.bktohm);
        rl=root.findViewById(R.id.rlabout);
        r2=root.findViewById(R.id.rllogout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), aboutuspg.class);
                startActivity(i1);
            }
        });
r2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i1=new Intent(getActivity(), MainActivity.class);
        startActivity(i1);
    }
});
t1.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v){
Intent intent=new Intent(getActivity(),home_activity.class);
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
                Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });




        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        return root;
    }
}

