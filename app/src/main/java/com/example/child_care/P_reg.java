package com.example.child_care;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class P_reg extends AppCompatActivity {

    EditText u_name,u_email,u_pass,u_add,u_phone;
    Button signup;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ImageView userphoto;
    static int PReqCode=1;
    static int REQUESCODE=1;
    Uri picked;
    LazyLoader lazyLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_reg);

        lazyLoader=(LazyLoader)findViewById(R.id.progg);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);

        u_name=(EditText)findViewById(R.id.txt_username);
        u_email=(EditText)findViewById(R.id.txt_email);
        u_pass=(EditText)findViewById(R.id.txt_password);
        u_add=(EditText)findViewById(R.id.txt_address);
        u_phone=(EditText)findViewById(R.id.txt_phone);
        userphoto=findViewById(R.id.imageView2);
        signup=(Button)findViewById(R.id.signup);

        userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=22){
                    checkAndRequestForPermission();
                }
                else{
                    openGallery();
                }

            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("People");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name= u_name.getText().toString();
                final String email= u_email.getText().toString().trim();
                String pass= u_pass.getText().toString().trim();
                final String address= u_add.getText().toString();
                final String phone =u_phone.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Name",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(),"Please Enter A Valid Email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(pass.length()<6){
                    Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_LONG).show();
                }

                if(TextUtils.isEmpty(address)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Address",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Phone",Toast.LENGTH_LONG).show();
                    return;
                }

                lazyLoader.setVisibility(View.VISIBLE);


                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(P_reg.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                lazyLoader.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                 people info=new people(
                                   name,
                                   email,
                                   address,
                                   phone
                                 );



                                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });

                                    updateUserinfo(name,picked,firebaseAuth.getCurrentUser());



                                }

                            }
                        });


            }
        });





    }

    private void updateUserinfo(final String name, Uri picked, final FirebaseUser currentUser) {


        StorageReference mStorage= FirebaseStorage.getInstance().getReference().child("user_photos");
        final StorageReference imagefilepath=mStorage.child(picked.getLastPathSegment());
        imagefilepath.putFile(picked).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imagefilepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileUpdate=new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            lazyLoader.setVisibility(View.GONE);
                                            Toast.makeText(P_reg.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                            udateui();


                                        }

                                    }
                                });
                    }
                });

            }
        });


    }

        private void udateui() {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),parents.class));
        }


    private void checkAndRequestForPermission() {

        if(ContextCompat.checkSelfPermission(P_reg.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(P_reg.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(P_reg.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
            }

            else{
                ActivityCompat.requestPermissions(P_reg.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }
        else{
            openGallery();
        }




    }

    private void openGallery() {

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK&& requestCode==REQUESCODE&& data!=null){

            picked=data.getData();
            userphoto.setImageURI(picked);

        }
    }
}
