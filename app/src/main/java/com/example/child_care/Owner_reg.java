package com.example.child_care;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.chaos.view.PinView;
import com.example.child_care.Interfaces.OnUserActionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Owner_reg extends AppCompatActivity {
    EditText u_name,u_email,u_pass,u_phone,specialist;
    private String name,email,phoneN,pass,type;
    Button signup,letsGoBtn;
    PinView pinView;
    TextView timer,specialistHeading;
    LinearLayout phoneSection;
    RelativeLayout codeSection;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,mReference;
    private String mVerificationId;
    private ProgressDialog progressDialog;
    ImageView userphoto;
    static int PReqCode=1;
    static int REQUESCODE=1;
    Uri picked;
    LazyLoader lazyLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_reg);

        lazyLoader=(LazyLoader)findViewById(R.id.progg);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(200);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            type = myIntent.getStringExtra("type");
        }

        lazyLoader.addView(loader);

        u_name=(EditText)findViewById(R.id.txt_username);
        u_email=(EditText)findViewById(R.id.txt_email);
        u_pass=(EditText)findViewById(R.id.txt_password);
        u_phone=(EditText)findViewById(R.id.txt_phone);
        userphoto=findViewById(R.id.imageView2);
        codeSection= findViewById(R.id.codeSection);
        phoneSection= findViewById(R.id.number_section);
        pinView= findViewById(R.id.pinView);
        timer= findViewById(R.id.timer_reg);
        signup= findViewById(R.id.signup);
        letsGoBtn= findViewById(R.id.letsGO_btn);
        specialist= findViewById(R.id.txt_specialist);
        specialistHeading= findViewById(R.id.txt_specialist_heading);

        if (type.equals("Doctor")){
            specialist.setVisibility(View.VISIBLE);
            specialistHeading.setVisibility(View.VISIBLE);
        }
        else {
            specialist.setVisibility(View.GONE);
            specialistHeading.setVisibility(View.GONE);
        }

        mReference = FirebaseDatabase.getInstance().getReference().child("Authority");
        progressDialog = new ProgressDialog(this);

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

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Authority");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 name= u_name.getText().toString();
                 email= u_email.getText().toString().trim();
                 pass= u_pass.getText().toString().trim();
                 phoneN =u_phone.getText().toString();

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
                if(TextUtils.isEmpty(phoneN)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Phone",Toast.LENGTH_LONG).show();
                    return;
                }
                else {

                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Checking your Information");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    userNumberExist(u_phone.getText().toString(), new OnUserActionListener() {
                        @Override
                        public void onExists(Boolean exists) {
                            if (exists) {
                                progressDialog.dismiss();
                                Toast.makeText(Owner_reg.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            } else {

                                progressDialog.dismiss();
                                new CountDownTimer(60000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        timer.setText("Wait "+millisUntilFinished / 1000+" Seconds");
                                    }

                                    public void onFinish() {
                                        timer.setText("Go Back and Try again");
                                    }
                                }.start();
                                phoneSection.setVisibility(View.GONE);
                                codeSection.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                                sendVerificationCode(u_phone.getText().toString());
                            }
                        }

                    });

                }

            }
        });


        letsGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_code = pinView.getText().toString();
                if (v_code.isEmpty()){
                    pinView.setError("Enter Verification Code");
                    Toast.makeText(getApplicationContext(), "Enter Verification Code", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("We are creating new account for you");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    verifyVerificationCode(v_code);
                }

            }
        });





    }

    private void updateUserinfo(final String name, Uri picked, final FirebaseUser currentUser) {


        StorageReference mStorage= FirebaseStorage.getInstance().getReference().child("owner_photos");
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
                                            progressDialog.dismiss();
                                            Toast.makeText(Owner_reg.this,"Registration Complete",Toast.LENGTH_SHORT).show();
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
        //FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Owner_reg.this, owner.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Owner_reg.this.finish();
    }


    private void openGallery() {

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }


    private void userNumberExist(final String phone, final OnUserActionListener listener) {
        databaseReference.orderByChild("phone")
                .equalTo(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listener.onExists(dataSnapshot.exists());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Owner_reg.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }




    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinView.setText(code);
                //verifying the code

                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("We are creating new account for you");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                verifyVerificationCode(code);

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Owner_reg.this,e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Owner_reg.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Register User

                            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(Owner_reg.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            lazyLoader.setVisibility(View.GONE);

                                            if (task.isSuccessful()) {

                                                Ownerr info=new Ownerr(
                                                        name,email,phoneN,type,specialist.getText().toString()
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

                        } else {

                            progressDialog.dismiss();
                            Toast.makeText(Owner_reg.this, "Invalid verification Code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void checkAndRequestForPermission() {

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Owner_reg.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(Owner_reg.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
            }

            else{
                ActivityCompat.requestPermissions(Owner_reg.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }
        else{
            openGallery();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK&& requestCode==REQUESCODE&& data!=null){

            picked=data.getData();
            userphoto.setImageURI(picked);

        }
    }



    public void OpenLogin(View view) {
        startActivity(new Intent(Owner_reg.this,owner.class));
        finish();
    }

}



