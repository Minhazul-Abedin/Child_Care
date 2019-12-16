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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class owner extends AppCompatActivity {

    Button o_sign,o_forgot,o_login;
    LazyLoader lazyLoader;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    EditText u_email,u_pass;
    FirebaseUser mUser;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        o_sign=(Button) findViewById(R.id.sign);
        o_login=(Button) findViewById(R.id.login);
        o_forgot=(Button)findViewById(R.id.forgot);

        u_email=(EditText)findViewById(R.id.txt_email);
        u_pass=(EditText)findViewById(R.id.txt_password);

        firebaseAuth= FirebaseAuth.getInstance();

        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            type = myIntent.getStringExtra("type");
        }
        FirebaseUser user= firebaseAuth.getCurrentUser();



        lazyLoader=(LazyLoader)findViewById(R.id.progg);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);


        o_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nk=new Intent(getApplicationContext(),forgot.class);
                startActivity(nk);
            }
        });

        o_login.setOnClickListener(new View.OnClickListener() {
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
                        .addOnCompleteListener((Activity) owner.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                lazyLoader.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    mUser = firebaseAuth.getCurrentUser();

                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Authority");

                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            String type = dataSnapshot.child(mUser.getUid()).child("type").getValue(String.class);

                                            if(type.equals("Doctor")){
                                                startActivity(new Intent(owner.this,dochome.class));
                                                finish();
                                            } else if (type.equals("Tutor")){
                                                startActivity(new Intent(owner.this,tuitor_home.class));
                                                finish();
                                            } else {
                                                startActivity(new Intent(owner.this,owner_home.class));
                                                finish();
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(getApplicationContext(), "Something went wrong. Please try agin later", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    // Sign in success, update UI with the signed-in user's information

                                } else {
                                    Toast.makeText(getApplicationContext(),"Wrong Username Or Password",Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });

            }
        });





        o_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent na= new Intent(getApplicationContext(),Owner_reg.class).putExtra("type",type);
                startActivity(na);
            }
        });

    }
}
