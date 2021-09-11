package com.example.mess;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import com.example.mess.Model.Customers;
import com.example.mess.Model.Menus;
import com.example.mess.Model.Orderstatus;
import com.example.mess.Model.Requests;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import com.example.mess.Model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class adapterorder extends RecyclerView.Adapter<adapterorder.ViewHolder> {
    public Context context;
    ArrayList<Requests> listData;

     String a, b,email;
    Integer c, d;
    Integer e;

    public adapterorder(Context context, ArrayList<Requests> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Requests requests = listData.get(position);
        holder.txtname.setText(requests.getFoodName());
        email=requests.getEmail();
        holder.custname.setText(requests.getCustomerName());
        holder.txtprice.setText(requests.getTotal().toString());
        holder.txtqty.setText(requests.getQuantity().toString());
        holder.messname.setText(requests.getMessName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtname, txtprice, txtqty,custname,messname;

        private Button b1, b2;
        FirebaseUser user;
        FirebaseAuth auth;
        Orderstatus orderstatus;
        Requests req;
        DatabaseReference myRef, demoRef;



        public ViewHolder(View itemView) {
            super(itemView);


            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();
            //String email = user.getEmail();


            txtname = (TextView) itemView.findViewById(R.id.textView13);
            custname = (TextView) itemView.findViewById(R.id.Custname);
            txtprice = (TextView) itemView.findViewById(R.id.price);
            txtqty = (TextView) itemView.findViewById(R.id.qty);
            messname = (TextView) itemView.findViewById(R.id.messname);

            //Toast.makeText(itemView.getContext(),c,Toast.LENGTH_SHORT).show();
            b2 = (Button) itemView.findViewById(R.id.Button3);
            b1 = (Button) itemView.findViewById(R.id.Button2);
            orderstatus = new Orderstatus();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child("Orderstatus");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String c;
                    c=email;
                    Intent i1=new Intent(context,Timeorder.class);
                    i1.putExtra("fname",txtname.getText().toString());
                    i1.putExtra("custname",custname.getText().toString());
                    i1.putExtra("fprice",Integer.parseInt(txtprice.getText().toString()));
                    i1.putExtra("fqty",Integer.parseInt(txtqty.getText().toString()));
                    i1.putExtra("messname",messname.getText().toString());
                    i1.putExtra("email",c);
                    context.startActivity(i1);
                }
            });
          b2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
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
                        if(a.equals(custname) && b.equals(txtname))
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
}

