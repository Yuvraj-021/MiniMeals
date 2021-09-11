package com.example.mini;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini.Model.OrderStatus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orderstatusmain extends AppCompatActivity {
    ArrayList<OrderStatus> listData;
    private RecyclerView rv;
    private OrderStatusadapter adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String a;
    LinearLayout lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderstatusrec);


        lm= (LinearLayout) findViewById(R.id.orderlinearlayout  );

        rv=new RecyclerView(getApplicationContext());
        // rv = (RecyclerView) root.findViewById(R.id.recycler_view1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(Orderstatusmain.this));

        listData = new ArrayList<OrderStatus>();

        DividerItemDecoration decoration = new DividerItemDecoration(Orderstatusmain.this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        a=firebaseUser.getEmail();

        lm.addView(rv);



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Orderstatus");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("1")) {
                    displayrequests();
                }
                else
                {
                    alterrequests();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayrequests() {
        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Orderstatus");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    OrderStatus l = npsnapshot.getValue(OrderStatus.class);
                    if (l.getEmail().contains(a.toString())) {
                        listData.add(l);
                    }
                }
                adapter = new OrderStatusadapter(Orderstatusmain.this, listData);
                rv.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Orderstatusmain.this, "opps.....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void alterrequests()
    {

        ImageView imagebyCode = new ImageView(Orderstatusmain.this);
        imagebyCode.setImageResource(R.drawable.cart);
        LinearLayout.LayoutParams params =  new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imagebyCode.setLayoutParams(params);
        params.gravity= Gravity.CENTER;
        lm.addView(imagebyCode);

        TextView textView = new TextView(Orderstatusmain.this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(30);
        textView.setTextColor(Color.rgb(0,0,0));
        Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.lobster_regular);
        textView.setTypeface(typeface);
        textView.setText("Cart is Empty");
        lm.addView(textView);

    }

}

