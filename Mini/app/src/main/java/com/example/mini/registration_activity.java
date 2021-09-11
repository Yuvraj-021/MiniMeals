package com.example.mini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mini.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class

registration_activity extends AppCompatActivity {
    TextView t1;
EditText e1,e2,e3,e4,e5,e6;
Button b1;
    private FirebaseAuth mAuth;
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
    t1=(TextView)findViewById(R.id.textView2);
    e1=(EditText)findViewById(R.id.editText);
    e2=(EditText)findViewById(R.id.editText2);
    e3=(EditText)findViewById(R.id.editText3);
    e4=(EditText)findViewById(R.id.editText4);
    e5=(EditText)findViewById(R.id.editText5);
    e6=(EditText)findViewById(R.id.editText6);
    b1=(Button)findViewById(R.id.button);
    mAuth = FirebaseAuth.getInstance();
    final FirebaseDatabase database= FirebaseDatabase.getInstance();
    final DatabaseReference ref=database.getReference("customers");
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            registerUser();
        }
    });

    t1.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(registration_activity.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
    private void registerUser() {
        final String Username = e1.getText().toString().trim();
        final String email = e2.getText().toString().trim();
        final String phone = e3.getText().toString().trim();
        final String address = e4.getText().toString().trim();
         String passwd = e5.getText().toString().trim();
        final String password = e6.getText().toString().trim();


        if (Username.isEmpty()) {
            e1.setError(getString(R.string.input_error_name));
            e1.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            e4.setError(getString(R.string.input_error_name));
            e4.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            e2.setError(getString(R.string.input_error_email));
            e2.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e2.setError(getString(R.string.input_error_email_invalid));
            e2.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            e6.setError(getString(R.string.input_error_password));
            e6.requestFocus();
            return;
        }
        if (passwd.isEmpty()) {
            e5.setError(getString(R.string.input_error_password));
            e5.requestFocus();
            return;
        }
if(!passwd.equals(password)){
    e5.setError("Password is not same !!!");
    e5.requestFocus();
    e6.setError("Password is not same !!!");
    e6.requestFocus();
    return;
}
        if (passwd.length() < 6) {
            e5.setError(getString(R.string.input_error_password_length));
            e5.requestFocus();
            return;
        }
        if (password.length() < 6) {
            e6.setError(getString(R.string.input_error_password_length));
            e6.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            e3.setError(getString(R.string.input_error_phone));
            e3.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            e3.setError(getString(R.string.input_error_phone_invalid));
            e3.requestFocus();
            return;
        }


        final ProgressDialog mdialog=new ProgressDialog(registration_activity.this);
        mdialog.setMessage("Please Wait");
        mdialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Users user = new Users(Username,email,phone,address,password);
                            FirebaseDatabase.getInstance().getReference("customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mdialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registration_activity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(registration_activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}