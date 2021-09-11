package com.example.mini.homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini.Model.Messusers;
import com.example.mini.Model.Users;
import com.example.mini.R;
import com.example.mini.menufood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Random;

public class menuadapter extends RecyclerView.Adapter<menuadapter.ViewHolder> {
public Context context;
        ArrayList<Messusers> listData;
    String a,b;
    int imageId;


    public menuadapter(Context context, ArrayList<Messusers> listData) {
        this.listData = listData;
        this.context = context;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditems,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Messusers messusers=listData.get(position);
        holder.messnme.setText(messusers.getMessName());
        holder.type.setText(messusers.getMessType());

    holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            a=messusers.getMessName();


            Intent intent = new Intent(context.getApplicationContext(), menufood.class);
            intent.putExtra("mname",a);
            intent.putExtra("imgid",imageId);
            context.startActivity(intent);
        }
    });
        }

@Override
public int getItemCount() {
        return listData.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView type,messnme,t9;
    private ImageView mImageView;
    public RelativeLayout relativeLayout;
    private Button b1;




 public  int[] images= new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3,R.drawable.img4, R.drawable.img5, R.drawable.img6,R.drawable.img7, R.drawable.img8, R.drawable.img9,
            R.drawable.img10, R.drawable.img11, R.drawable.img12,R.drawable.img13, R.drawable.img14, R.drawable.img15,R.drawable.img16, R.drawable.img17, R.drawable.img18,
            R.drawable.img19, R.drawable.img20};

 public String fooditems[]={"Malai kofta ","Veg Biryani","Veg Afgani","Uttapam","Butter Dosa","Idli Sambhar","Aloo Gobi Masala","Channa masala"," Mix-Veg","Rajma masala"," Dhal Fry"," Dhal palak","Pav bhaji","Paneer","Dum Biryani","Rice"};
public String Sweets[]={"Balushahi","Boondi","Gulab jamun","Jalebi"};


    public ViewHolder(View itemView) {
        super(itemView);

        messnme=itemView.findViewById(R.id.messname);
        type=itemView.findViewById(R.id.nonveg);
        relativeLayout=itemView.findViewById(R.id.al);
        b1=itemView.findViewById(R.id.shop);
        TextView tv=itemView.findViewById(R.id.todaysspecial);
        TextView ts=itemView.findViewById(R.id.sweet);

        mImageView = (ImageView)itemView.findViewById(R.id.imgview);
         imageId = (int)(Math.random() * images.length);
        mImageView.setBackgroundResource(images[imageId]);

        Random random = new Random();
        int index = random.nextInt(fooditems.length - 0) + 0;
        tv.setText(fooditems[index]);

        Random ran = new Random();
        int i = ran.nextInt(Sweets.length - 0) + 0;
        ts.setText(Sweets[i]);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(context, menufood.class);
                i1.putExtra("mname",messnme.getText().toString());
                context.startActivity(i1);
            }
        });
    }
}
}