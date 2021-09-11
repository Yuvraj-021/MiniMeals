package com.example.mess;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import com.example.mess.Model.Menus;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

public class adaptermenu extends RecyclerView.Adapter<adaptermenu.ViewHolder> {
    public Context context;
     ArrayList<Menus> listData;

    public adaptermenu(Context context, ArrayList<Menus> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menuview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menus menus=listData.get(position);
        holder.txtname.setText(menus.getFoodName());
        holder.txtprice.setText(menus.getPrice().toString());
        holder.txtdescription.setText(menus.getDescription());
        Picasso.with(context)
                .load(menus.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageview1);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtprice,txtdescription;
        private ImageView imageview1;
         String id;
        private ImageButton b1,b2;
        public ViewHolder(View itemView) {
            super(itemView);
            imageview1=(ImageView) itemView.findViewById(R.id.imageView2);
            txtname=(TextView)itemView.findViewById(R.id.textView13);
            txtprice=(TextView)itemView.findViewById(R.id.textView14);
            txtdescription=(TextView)itemView.findViewById(R.id.textView17);

            b1=(ImageButton)itemView.findViewById(R.id.imageButton2);
            b2=(ImageButton)itemView.findViewById(R.id.imageButton3);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1=new Intent(context,updateMenu.class);
                    i1.putExtra("fname",txtname.getText().toString());
                    i1.putExtra("fprice",Integer.parseInt(txtprice.getText().toString()));
                   // i1.putExtra("fid",Integer.parseInt(txtid.getText().toString()));
                    context.startActivity(i1);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query query = ref.child("Menus").orderByChild("foodName").equalTo(txtname.getText().toString());

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });

        }

    }
}

