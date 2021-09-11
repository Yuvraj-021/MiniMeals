package com.example.mess;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mess.Model.Menus;
import com.example.mess.Model.SupplierMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterfooditems extends RecyclerView.Adapter<adapterfooditems.ViewHolder>  {
    public Context context;
    ArrayList<SupplierMenu> listData;

    public adapterfooditems(Context context, ArrayList<SupplierMenu> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterfooditems.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsfood,parent,false);
        return new adapterfooditems.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull adapterfooditems.ViewHolder holder, int position) {
        SupplierMenu supplierMenu=listData.get(position);
        holder.txtname.setText(supplierMenu.getFoodName());
        holder.txtprice.setText(supplierMenu.getPrice().toString());
        holder.txtdes.setText(supplierMenu.getDescription());
        holder.txtqty.setText(supplierMenu.getQuantity().toString());
        Picasso.with(context)
                .load(supplierMenu.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageview1);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtprice,txtdes,txtqty;
        private ImageView imageview1;
        String id;
        private Button b1,b2;
        public ViewHolder(View itemView) {
            super(itemView);
            imageview1=(ImageView) itemView.findViewById(R.id.imgview);
            txtname=(TextView)itemView.findViewById(R.id.foodname);
            txtdes=(TextView)itemView.findViewById(R.id.desc);
            txtqty=(TextView)itemView.findViewById(R.id.qty);
            txtprice=(TextView)itemView.findViewById(R.id.price);

            b1=itemView.findViewById(R.id.addb);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1=new Intent(context,Recieptpage.class);
                    i1.putExtra("fname",txtname.getText().toString());
                    i1.putExtra("desc",txtdes.getText().toString());
                    i1.putExtra("qty",Integer.parseInt(txtqty.getText().toString()));
                    i1.putExtra("fprice",Integer.parseInt(txtprice.getText().toString()));
                    context.startActivity(i1);
                }
            });
        }

    }
}
