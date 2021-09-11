package com.example.minimeals;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    EditText e1,e2;
    Button b1;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText10);
        b1=(Button)findViewById(R.id.button4);
        t1=(TextView)findViewById(R.id.textView10);
       // t2=(TextView)findViewById(R.id.textView11);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference ref=database.getReference("customers");
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                   // Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog=new ProgressDialog(MainActivity.this);
                mdialog.setMessage("Please Wait");
                mdialog.show();
                String userEmail = e1.getText().toString();
                String userPaswd = e2.getText().toString();
                if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (userPaswd.isEmpty()) {
                    e2.setError("Enter Password!");
                    e2.requestFocus();
                } else if (userEmail.isEmpty()) {
                    e1.setError("Provide your Email first!");
                    e1.requestFocus();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            mdialog.dismiss();
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, home_activity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,registration_activity.class);
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

