package com.example.mess.ui.home;


import android.content.Intent;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.mess.R;
import com.example.mess.addMenu;
import com.example.mess.fooditems;
import com.example.mess.reqfood;
import com.example.mess.viewMenu;

public class HomeFragment extends Fragment {

    ImageView add,update,view,delete;
    LinearLayout ll,kl,sl,tl;

    ImageView add_img,update_img,view_img,delete_img;

    TextView atv,utv,vtv,dtv;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        ll = root.findViewById(R.id.al);
        kl =root. findViewById(R.id.vl);
        sl=root.findViewById(R.id.rl);
        tl=root.findViewById(R.id.fl);


        add_img = root.findViewById(R.id.addmenu_img);
        view_img = root.findViewById(R.id.viewmenu_img);


        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),addMenu.class);
                startActivity(i1);
            }
        });
        kl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1=new Intent(getActivity(), viewMenu.class);
                        startActivity(i1);
                    }
                });

        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), reqfood.class);
                startActivity(i1);
            }
        });
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), fooditems.class);
                startActivity(i1);
            }
        });


        return root;
    }

}