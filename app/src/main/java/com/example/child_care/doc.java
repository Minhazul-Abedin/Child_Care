package com.example.child_care;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class doc extends AppCompatActivity {
    Button sign,login,forgot;
    LazyLoader lazyLoader;
    private FirebaseAuth firebaseAuth;
    EditText u_email,u_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

       sign =(Button)findViewById(R.id.sign);
       login =(Button)findViewById(R.id.login);
       forgot=(Button)findViewById(R.id.forgot);
        u_email=(EditText)findViewById(R.id.txt_email);
        u_pass=(EditText)findViewById(R.id.txt_password);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        lazyLoader=(LazyLoader)findViewById(R.id.progg);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot=new Intent(getApplicationContext(), forgot.class);
                startActivity(forgot);
            }
        });



        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signn=new Intent(doc.this,D_reg.class);
                startActivity(signn);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=u_email.getText().toString().trim();
                String pass=u_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                lazyLoader.setVisibility(View.VISIBLE);



                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener((Activity) doc.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                lazyLoader.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    finish();
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(doc.this,dochome.class));
                                } else {
                                    Toast.makeText(getApplicationContext(),"Wrong Username Or Password",Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });

            }
        });
    }
}
