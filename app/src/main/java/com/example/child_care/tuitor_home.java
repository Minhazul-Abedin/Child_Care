package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tuitor_home extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4,bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuitor_home);

        bt1=(Button)findViewById(R.id.btn_1st);
        bt2=(Button)findViewById(R.id.btn_2nd);
        bt3=(Button)findViewById(R.id.btn_3rd);
        bt4=(Button)findViewById(R.id.btn_4rth);
        bt5=(Button)findViewById(R.id.btn_5th);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cc=new Intent(getApplicationContext(),child_show.class);
                startActivity(cc);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acc=new Intent(getApplicationContext(),ChildListForTutorActivity.class);
                startActivity(acc);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qqcc=new Intent(getApplicationContext(),authority.class);
                startActivity(qqcc);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cc=new Intent(getApplicationContext(),about.class);
                startActivity(cc);
            }
        });


    }
}
