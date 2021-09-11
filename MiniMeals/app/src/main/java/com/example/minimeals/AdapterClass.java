package com.example.minimeals;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimeals.model.User1;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
String a,b;
    public Context context;
    ArrayList<User1> list;
    public AdapterClass(Context context,ArrayList<User1> list){
        this.context = context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder_for_search,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

holder.messnm.setText(list.get(i).getMessName());
holder.mtype.setText(list.get(i).getMessType());
holder.madd.setText(list.get(i).getMessaddress());

        a=holder.messnm.getText().toString();
        b=holder.madd.getText().toString();
holder.cv1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context.getApplicationContext(),mess_order.class);
        intent.putExtra("messname",a);
        intent.putExtra("address",b);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView messnm,mtype,madd;
      private CardView cv1;
     public MyViewHolder(View itemView){
         super(itemView);
         messnm=itemView.findViewById(R.id.messnm);
         mtype=itemView.findViewById(R.id.messtp);
         madd=itemView.findViewById(R.id.messad);
         cv1=itemView.findViewById(R.id.cardv);
     }
 }
}
