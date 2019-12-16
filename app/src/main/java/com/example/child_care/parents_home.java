package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class parents_home extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_home);
        bt1=(Button)findViewById(R.id.btn_1st);
        bt2=(Button)findViewById(R.id.btn_2nd);
        bt3=(Button)findViewById(R.id.btn_3rd);
        bt4=(Button)findViewById(R.id.btn_4rth);
        bt5=(Button)findViewById(R.id.btn_5th);
        bt6=(Button)findViewById(R.id.btn_6th);
        bt7=(Button)findViewById(R.id.btn_7th);

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent annn=new Intent(parents_home.this,about.class);
                startActivity(annn);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bab=new Intent(getApplicationContext(),Baby.class);
                startActivity(bab);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent baab=new Intent(getApplicationContext(),docshow.class);
                startActivity(baab);


            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authority=new Intent(getApplicationContext(),authority.class);
                startActivity(authority);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent asad=new Intent(getApplicationContext(),tuitor_show.class);
                startActivity(asad);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service=new Intent(getApplicationContext(),report_chosse.class);
                startActivity(service);
            }
        });

    }
}
