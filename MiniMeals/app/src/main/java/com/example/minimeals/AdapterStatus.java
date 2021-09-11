package com.example.minimeals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimeals.model.OrderStatus;

import java.util.ArrayList;

public class AdapterStatus extends RecyclerView.Adapter<AdapterStatus.ViewHolder>{
public Context context;
        String a,b,c,d,e;
        ArrayList<OrderStatus> listData;
public AdapterStatus(Context context, ArrayList<OrderStatus> listData) {
        this.listData = listData;
        this.context = context;
        }

    @NonNull
    @Override
    public AdapterStatus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatus orders=listData.get(position);
    a=orders.getMessname();
    b=orders.getFname();
    c=orders.getStatus();
        holder.oredrinfo.setText("Your order from "+a+" for "+b+" is "+c);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView oredrinfo;
        public ViewHolder(View itemView) {
            super(itemView);
            oredrinfo=(TextView)itemView.findViewById(R.id.mess_notify);
        }
    }
}