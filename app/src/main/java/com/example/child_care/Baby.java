package com.example.child_care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Baby extends AppCompatActivity {

    EditText u_name,u_age,u_birth,u_blood;
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
        setContentView(R.layout.activity_baby);

        u_name=(EditText)findViewById(R.id.txt_username);
        u_age=(EditText)findViewById(R.id.txt_age);
        u_birth=(EditText)findViewById(R.id.txt_birth);
        u_blood=(EditText)findViewById(R.id.txt_blood);
        signup=(Button)findViewById(R.id.signup);
        userphoto=findViewById(R.id.imageView2);

        lazyLoader=(LazyLoader)findViewById(R.id.progg);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Baby");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name= u_name.getText().toString();
                final String age= u_age.getText().toString();
                final String birthday= u_birth.getText().toString();
                final String blood =u_blood.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext()," Your Child's Name",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(age)){
                    Toast.makeText(getApplicationContext(),"Your Child's Age",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(birthday)){
                    Toast.makeText(getApplicationContext(),"Your Child's Birthday",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(blood)){
                    Toast.makeText(getApplicationContext(),"Your Child's blood",Toast.LENGTH_LONG).show();
                    return;
                }
                babyinfo babyinfo=new babyinfo(

                  name,age,birthday,blood
                );
                databaseReference.child(name).setValue(babyinfo);
                Toast.makeText(Baby.this,"Information Uploaded",Toast.LENGTH_SHORT).show();
                finish();
                Intent aaa=new Intent(getApplicationContext(),parents_home.class);
                startActivity(aaa);


            }
        });





    }
}
