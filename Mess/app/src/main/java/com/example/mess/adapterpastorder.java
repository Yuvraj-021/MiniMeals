package com.example.mess;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mess.Model.Orderstatus;


import java.util.ArrayList;

public class adapterpastorder extends RecyclerView.Adapter<adapterpastorder.ViewHolder> {

    public Context context;
    ArrayList<Orderstatus> listData;

    public adapterpastorder(Context context, ArrayList<Orderstatus> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterpastorder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pastorderitems,parent,false);
        return new adapterpastorder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterpastorder.ViewHolder holder, int position) {
        Orderstatus orderstatus=listData.get(position);
        holder.txtname.setText(orderstatus.getFname());
        holder.txtqty.setText(orderstatus.getQuantity().toString());
        holder.txtprice.setText(orderstatus.getTotal().toString());
        holder.status.setText(orderstatus.getStatus());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtprice,txtqty,status;

        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.ordername);
            txtprice=(TextView)itemView.findViewById(R.id.price);
            txtqty=(TextView)itemView.findViewById(R.id.qty);
            status=(TextView)itemView.findViewById(R.id.stat);

        }

    }
}
