package com.example.mess;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mess.Model.Menus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;


public class updateMenu extends AppCompatActivity {


    private Button btnupdate;
    private Uri imgUrl;
    private EditText ed1, ed2;
    DatabaseReference myRef, demoRef;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser user;

    FirebaseAuth auth;
    private Intent data;

    int var=0;
    private static final int CHOOSE_IMAGE = 1;
    private StorageTask mUploadTask;

    String Foodname;
    Integer price,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepg);

        Foodname=getIntent().getStringExtra("fname");
        price=getIntent().getIntExtra("fprice",0);
        id=getIntent().getIntExtra("fid",0);


        ed1 = findViewById(R.id.editTextu2);
        ed2 = findViewById(R.id.editText);
        btnupdate =findViewById(R.id.buttonb2);

        ed1.setText(Foodname);
        ed2.setText(price.toString());
        //ed2.setText(price);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Menus");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final String a= ed1.getText().toString();
              final Integer b=Integer.parseInt(ed2.getText().toString());

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query query = ref.child("Menus").orderByChild("foodName").equalTo(Foodname);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().child("foodName").setValue(a);
                            appleSnapshot.getRef().child("price").setValue(b);
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