package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phone extends AppCompatActivity {
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        /*mAuth=FirebaseAuth.getInstance();
        editText=(EditText)findViewById(R.id.editTextCode);
        progressBar=findViewById(R.id.progressbar);
        String phonenumber=getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);*//*
        findViewById(R.id.buttonletsgo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

String code=editText.getText().toString().trim();

if(code.isEmpty()|| code.length()<6){

        editText.setError("Enter code...");
        editText.requestFocus();
        return;
    }
    //progressBar.setVisibility(View.VISIBLE);
    verifyCode(code);
}
        });*/
    }

    private void verifyCode(String code){
PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){

                    Intent intent=new Intent(getApplicationContext(),owner.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                }
                else {
                    Toast.makeText(phone.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendVerificationCode(String number){
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback);


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
               // progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(phone.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };
}
