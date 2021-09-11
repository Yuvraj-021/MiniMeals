package com.example.mess;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mess.Model.Users;
import com.example.mess.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class login_act extends AppCompatActivity {
    TextView t1,t2;
    EditText e1,e2;
    Button b1;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String userid;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpg);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText10);
        b1=(Button)findViewById(R.id.button4);
        t1=(TextView)findViewById(R.id.textView10);
        t2=(TextView)findViewById(R.id.textView11);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference("Users");



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user= firebaseAuth.getCurrentUser();

                if (user != null) {
                 Toast.makeText(login_act.this, "User logged in ", Toast.LENGTH_SHORT).show();
                 // Intent I = new Intent(login_act.this, MainActivity.class);
                  //startActivity(I);
                } else {
                    Toast.makeText(login_act.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog=new ProgressDialog(login_act.this);
                mdialog.setMessage("Please Wait");
                mdialog.show();
                String userEmail = e1.getText().toString();
                String userPaswd = e2.getText().toString();
                if (userEmail.isEmpty()) {
                    e1.setError("Provide your Email first!");
                    e1.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    e2.setError("Enter Password!");
                    e2.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(login_act.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(login_act.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            mdialog.dismiss();
                            if (!task.isSuccessful()) {
                                Toast.makeText(login_act.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(login_act.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(login_act.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(login_act.this, register_act.class);
                startActivity(intent);
            }
        });
        t2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(login_act.this, resetpasswordpg.class);
                startActivity(intent);
            }
        });

    }

   @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

}
