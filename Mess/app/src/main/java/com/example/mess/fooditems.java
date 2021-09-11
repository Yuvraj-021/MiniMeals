package com.example.mess;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mess.Model.Menus;
import com.example.mess.Model.SupplierMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fooditems extends AppCompatActivity {

    ArrayList<SupplierMenu>listData;
    private RecyclerView rv;
    private adapterfooditems adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemsfoodrr);

        getSupportActionBar().setTitle("Food Items");
            rv=(RecyclerView)findViewById(R.id.recycler_view);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(fooditems.this));
            listData=new ArrayList<SupplierMenu>();
            //  DividerItemDecoration decoration=new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
            // rv.addItemDecoration(decoration);

            final FirebaseUser user;
            FirebaseAuth auth;
            auth=FirebaseAuth.getInstance();
            user=auth.getCurrentUser();
            final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("SupplierMenu");
            nm.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        String userid=user.getUid();
                        SupplierMenu menus=new SupplierMenu();
                        SupplierMenu l=npsnapshot.getValue(SupplierMenu.class);
                            listData.add(l);
                    }
                    adapter=new adapterfooditems(fooditems.this,listData);
                    rv.setAdapter(adapter);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"opps.....",Toast.LENGTH_SHORT).show();
                }
            });



        }
}
