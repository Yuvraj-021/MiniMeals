package com.example.mini;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini.Model.Messusers;

import java.util.ArrayList;

public class searchadapter extends RecyclerView.Adapter<searchadapter.MyViewHolder> {
String a,b;
    public Context context;
    ArrayList<Messusers> list;
    public searchadapter(Context context, ArrayList<Messusers> list){
        this.context = context;
        this.list=list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.searchpg,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.messname.setText(list.get(i).getMessName());
        holder.messtype.setText(list.get(i).getMessType());
        holder.address.setText(list.get(i).getMessaddress());

        a=holder.messname.getText().toString();
       // b=holder.address.getText().toString();
        holder.cv1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context.getApplicationContext(),menufood.class);
        intent.putExtra("mname",a);
        //intent.putExtra("address",b);
        context.startActivity(intent);
    }
});


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView messname,messtype,address;
        private ImageView mImageView;
        private CardView cv1;

        int[] images = new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4, R.drawable.img5, R.drawable.img6,R.drawable.img7, R.drawable.img8, R.drawable.img9,
                R.drawable.img10, R.drawable.img11, R.drawable.img12,R.drawable.img13, R.drawable.img14, R.drawable.img15,R.drawable.img16, R.drawable.img17, R.drawable.img18,
                R.drawable.img19, R.drawable.img20};


     public MyViewHolder(View itemView){
         super(itemView);


         messname=itemView.findViewById(R.id.messnm);
         messtype=itemView.findViewById(R.id.messtype);
         address=itemView.findViewById(R.id.messadd);

         mImageView = (ImageView)itemView.findViewById(R.id.imageView2);

         int imageId = (int)(Math.random() * images.length);

         mImageView.setBackgroundResource(images[imageId]);
         cv1=itemView.findViewById(R.id.cardv);
     }
 }
}
