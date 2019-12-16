package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class dochome extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4,bt5,bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dochome);
        bt1=(Button)findViewById(R.id.btn_1st);
        bt2=(Button)findViewById(R.id.btn_2nd);
        bt3=(Button)findViewById(R.id.btn_3rd);
        bt4=(Button)findViewById(R.id.btn_4rth);
        bt5=(Button)findViewById(R.id.btn_5th);
        bt6=(Button)findViewById(R.id.btn_6th);

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent annn=new Intent(dochome.this,about.class);
                startActivity(annn);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent se=new Intent(getApplicationContext(),services.class);
                startActivity(se);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sea=new Intent(getApplicationContext(),child_show.class);
                startActivity(sea);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seq=new Intent(getApplicationContext(),authority.class);
                startActivity(seq);
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChildListForDoctorActivity.class));
            }
        });


    }
}
