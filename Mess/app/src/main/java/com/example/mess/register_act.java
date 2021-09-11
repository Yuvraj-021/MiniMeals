package com.example.mess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import com.example.mess.Model.Users;
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


public class register_act extends AppCompatActivity {
  TextView t1, t2;
  EditText e1, e2, e3, e4, e5, e6, e7;
  Button b1;
  CheckBox c1, c2;
  String res = "not Selected";
  AwesomeValidation awesomeValidation;
  private FirebaseAuth mAuth;
  FirebaseUser user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.registerp);

    if(getSupportActionBar()!=null)
    {
      getSupportActionBar().hide();
    }

    t2 = (TextView) findViewById(R.id.textView6);
    t1 = (TextView) findViewById(R.id.textView16);
    e1 = (EditText) findViewById(R.id.editText3);
    e2 = (EditText) findViewById(R.id.editText5);
    e3 = (EditText) findViewById(R.id.editText7);
    e4 = (EditText) findViewById(R.id.editText6);
    e5 = (EditText) findViewById(R.id.editText8);
    e6 = (EditText) findViewById(R.id.editText9);
   // e7 = (EditText) findViewById(R.id.editText11);
    b1 = (Button) findViewById(R.id.button);
    c1 = (CheckBox) findViewById(R.id.checkBox2);
    c2 = (CheckBox) findViewById(R.id.checkBox3);

    mAuth = FirebaseAuth.getInstance();
    user=mAuth.getCurrentUser();


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference("Users");

   b1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
       if(c1.isChecked())
       {
         res="veg";
       }
       if(c2.isChecked())
       {
         res="non-veg";
       }
       registerUser();
     }
   });


    t1.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(register_act.this, login_act.class);
        startActivity(intent);
      }
    });
  }


  private void registerUser() {
    final String Messname = e1.getText().toString().trim();
    final String Messaddress = e2.getText().toString().trim();
    final String Ownername = e3.getText().toString().trim();
    final String phone = e4.getText().toString().trim();
    final String email = e5.getText().toString().trim();
    String password = e6.getText().toString().trim();



    if (Messname.isEmpty()) {
      e1.setError(getString(R.string.input_error_name));
      e1.requestFocus();
      return;
    }
    if (Messaddress.isEmpty()) {
      e1.setError(getString(R.string.input_error_name));
      e1.requestFocus();
      return;
    }
    if (Ownername.isEmpty()) {
      e1.setError(getString(R.string.input_error_name));
      e1.requestFocus();
      return;
    }

    if (email.isEmpty()) {
      e5.setError(getString(R.string.input_error_email));
      e5.requestFocus();
      return;
    }

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      e5.setError(getString(R.string.input_error_email_invalid));
      e5.requestFocus();
      return;
    }

    if (password.isEmpty()) {
      e6.setError(getString(R.string.input_error_password));
      e6.requestFocus();
      return;
    }

    if (password.length() < 6) {
      e6.setError(getString(R.string.input_error_password_length));
      e6.requestFocus();
      return;
    }

    if (phone.isEmpty()) {
      e4.setError(getString(R.string.input_error_phone));
      e4.requestFocus();
      return;
    }

    if (phone.length() != 10) {
      e4.setError(getString(R.string.input_error_phone_invalid));
      e4.requestFocus();
      return;
    }


    final ProgressDialog mdialog=new ProgressDialog(register_act.this);
    mdialog.setMessage("Please Wait");
    mdialog.show();
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                  Users user = new Users(Messname,Messaddress,Ownername,phone,email,res);
                  FirebaseDatabase.getInstance().getReference("Users")
                          .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                          .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      mdialog.dismiss();
                      if (task.isSuccessful()) {
                        Toast.makeText(register_act.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                      } else {
                        //display a failure message
                      }
                    }
                  });

                } else {
                  Toast.makeText(register_act.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
              }
            });
  }


}