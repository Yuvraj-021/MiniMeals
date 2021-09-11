package com.example.minimeals.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimeals.AdapterClass;
import com.example.minimeals.R;
import com.example.minimeals.model.User1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    ArrayList<User1> list;
    private RecyclerView rv;
    SearchView sv;
    DatabaseReference nm;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        super.onCreate(savedInstanceState);
        rv = root.findViewById(R.id.rvsrch);
        sv = root.findViewById(R.id.searchView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        nm = FirebaseDatabase.getInstance().getReference().child("Users");
return root;
    }


    @Override
    public void onStart(){
        super.onStart();
        if (nm != null) {
            nm.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(User1.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(getActivity(),list);
                        rv.setAdapter(adapterClass);
                        rv.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (sv != null) {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }


    private void search(String str) {
        ArrayList<User1> mylist = new ArrayList<>();
        for (User1 object : list) {
            if (object.getMessName().contains(str) ||object.getMessType().contains(str) ||object.getMessaddress().contains(str)) {
                mylist.add(object);
            }

        }
        AdapterClass adapterClass = new AdapterClass(getActivity(),mylist);
        rv.setAdapter(adapterClass);
    }
}