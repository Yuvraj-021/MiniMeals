package com.example.mess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mess.Model.Menus;
import com.example.mess.Model.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.os.Handler;

import java.io.IOException;

import static java.sql.Types.NULL;


public class addMenu extends AppCompatActivity {


    private Button btnChoose, btnUpload;
    private ImageView imageView;
    private Uri imgUrl;
    private EditText ed1, ed2,ed3;
    DatabaseReference myRef, refg;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser user;
    Menus menus;
    FirebaseAuth auth;
    // private static final int PICK_IMAGE_REQUEST = 71;
    private Intent data;
    long id=0;
    long Menuid=0;
    String a,d;
    int b;
    private static final int CHOOSE_IMAGE = 1;
    private StorageTask mUploadTask;
    int c=0;
    String messname,useid;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmn);



        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText14);
        imageView =findViewById(R.id.imageView);
        btnChoose =findViewById(R.id.button2);
        btnUpload =findViewById(R.id.button3);


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Menus");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        menus =new Menus();
        users=new Users();

        refg = database.getReference("Users");
        useid=user.getUid();
        Toast.makeText(getApplicationContext(),useid,Toast.LENGTH_SHORT).show();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    messname= dataSnapshot.child(useid).child("messName").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });
    }
    private void chooseImage() {
        Intent i1 = new Intent();
        i1.setType("image/*");
        i1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i1, CHOOSE_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();

            Picasso.with(this).load(imgUrl).into(imageView);
        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    private void uploadImage() {
        if (imgUrl != null) {
            final ProgressDialog progressDialog = new ProgressDialog(addMenu.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final  StorageReference ref = storageReference.child("images/" + System.currentTimeMillis() + "." + GetFileExtension(imgUrl));
            mUploadTask=ref.putFile(imgUrl)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                                    a=ed1.getText().toString().trim();
                                    b=Integer.parseInt(ed2.getText().toString().trim());
                                    d = ed3.getText().toString().trim();
                                    menus.setFoodName(a);
                                    menus.setPrice(b);
                                    menus.setDescription(d);
                                    menus.setUserid(user.getUid());
                                    menus.setMessName(messname);

                                    //Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                                    //String generatedFilePath = downloadUri.getResult().toString();
                                    menus.setImageURL(uri.toString());
                                    c++;
                                    menus.setVal(c);


                                    //menus.setImageURL(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                                    myRef.child(String.valueOf(id+1)).setValue(menus);
                                    Toast.makeText(getApplicationContext(),"Data Inserted Successully",Toast.LENGTH_SHORT).show();
                                    imageView.setImageResource(R.drawable.altimg);

                                }
                            });
                        }})


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })


                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }


    }
}
