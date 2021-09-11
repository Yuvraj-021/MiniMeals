package com.example.minimeals;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimeals.model.User1;

import java.util.ArrayList;

public class adaptermenu extends RecyclerView.Adapter<adaptermenu.ViewHolder> implements Filterable {
    public Context context;
    String a,b;
    ArrayList<User1> listData;
ArrayList<User1> listfull;
    public adaptermenu(Context context, ArrayList<User1> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menuview_for_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final User1 us1=listData.get(position);
        holder.txtname.setText(us1.getMessName());
        holder.txttype.setText(us1.getMessType());
        holder.txtadd.setText(us1.getMessaddress());
       /* Picasso.with(context)
                .load(us1.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageview1);*/
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=us1.getMessName();
                b=us1.getMessaddress();

                Intent intent = new Intent(context.getApplicationContext(),mess_order.class);
                intent.putExtra("messname",a);
                intent.putExtra("address",b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    @Override
    public Filter getFilter(){
        return exFilter;
    }
private Filter exFilter=new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
         ArrayList<User1> filteredList=new ArrayList<>();
         if(charSequence==null || charSequence.length()==0){
             filteredList.addAll(listfull);
         }
         else
         {
             String filterPattern=charSequence.toString().toLowerCase().trim();
             for(User1 item : listfull){
                 if(item.getMessName().toLowerCase().contains(filterPattern)){
                     filteredList.add(item);
                 }
             }
         }
        FilterResults results=new FilterResults();
         results.values=filteredList;
         return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
listData.clear();
        listData.addAll((ArrayList)results.values);
        notifyDataSetChanged();
    }
};
    public class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout relativeLayout;
        private TextView txtimg,txtname,txttype,txtadd;
        //private ImageView imageview1;
        public ViewHolder(View itemView) {
            super(itemView);
            //imageview1=(ImageView) itemView.findViewById(R.id.imageView2);
            txtname=(TextView)itemView.findViewById(R.id.messnmh);
            txttype=(TextView)itemView.findViewById(R.id.typeh);
            txtadd=(TextView)itemView.findViewById(R.id.addressh);
            relativeLayout=itemView.findViewById(R.id.relat);


        }

    }
}

