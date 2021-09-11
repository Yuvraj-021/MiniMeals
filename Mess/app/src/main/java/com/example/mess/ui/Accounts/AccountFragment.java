package com.example.mess.ui.Accounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mess.MainActivity;
import com.example.mess.Model.Users;
import com.example.mess.R;
import com.example.mess.addMenu;
import com.example.mess.login_act;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import com.example.mess.aboutuspg;

public class AccountFragment extends Fragment {
    private TextView t1,t2,t3,t4,t5,t6;
    DatabaseReference myRef;
    FirebaseStorage storage;

    FirebaseUser user;
    Users users;
    FirebaseAuth auth;
    RelativeLayout rl,rll;
    String a,b,c,d,e,userid;
    private MainActivity act;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //act=(MainActivity)getActivity();
        //act.getSupportActionBar().hide();

        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        t1=(TextView)root.findViewById(R.id.acname);
        t2=(TextView)root.findViewById(R.id.acno);
        t3=(TextView)root.findViewById(R.id.acemail);
        t4=(TextView)root.findViewById(R.id.acaddress);
        t5=(TextView)root.findViewById(R.id.acprofile);
        t6=(TextView)root.findViewById(R.id.back);



         rl=root.findViewById(R.id.rlabout);
        rll=root.findViewById(R.id.rlogout);

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),MainActivity.class);
                startActivity(i1);
            }
        });

        rll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), login_act.class);
                startActivity(i1);
            }
        });

         rl.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i1=new Intent(getActivity(), aboutuspg.class);
                 startActivity(i1);
             }
         });

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userid=user.getUid();
        Toast.makeText(getActivity(),userid, Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        storage = FirebaseStorage.getInstance();

        users=new Users();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                    String name=dataSnapshot.child(userid).child("messName").getValue().toString();
                    String address=dataSnapshot.child(userid).child("messaddress").getValue().toString();
                    String ownername=dataSnapshot.child(userid).child("ownerName").getValue().toString();
                    String email=dataSnapshot.child(userid).child("email").getValue().toString();
                    String no=dataSnapshot.child(userid).child("mobileno").getValue().toString();

                    t5.setText(name);
                    t4.setText(address);
                    t1.setText(ownername);
                    t2.setText(no);
                    t3.setText(email);

                    Toast.makeText(getActivity(), "Profile generated", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });




        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        return root;
    }

    @Override
    public void onDestroy() {
        getActivity().getFragmentManager().popBackStack();
        super.onDestroy();

    }
}

