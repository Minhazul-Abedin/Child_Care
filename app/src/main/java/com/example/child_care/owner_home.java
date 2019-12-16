package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class owner_home extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        bt1=(Button)findViewById(R.id.btn_1st);
        bt2=(Button)findViewById(R.id.btn_2nd);
        bt3=(Button)findViewById(R.id.btn_3rd);
        bt4=(Button)findViewById(R.id.btn_4rth);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent baab=new Intent(getApplicationContext(),docshow.class);
                startActivity(baab);


            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent asad=new Intent(getApplicationContext(),tuitor_show.class);
                startActivity(asad);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daily=new Intent(getApplicationContext(),report_chosse.class);
                startActivity(daily);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent childinfo=new Intent(getApplicationContext(),child_show.class);
                startActivity(childinfo);
            }
        });

    }
}
