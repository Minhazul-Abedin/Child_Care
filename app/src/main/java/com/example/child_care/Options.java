package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Options extends AppCompatActivity {

    Button owner,doctor,tuitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        owner=(Button)findViewById(R.id.owner);
        doctor=(Button)findViewById(R.id.doc);
        tuitor=(Button)findViewById(R.id.tuitor);

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent owner=new Intent(getApplicationContext(),owner.class).putExtra("type","Owner");
                startActivity(owner);
                finish();
            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctor=new Intent(getApplicationContext(),owner.class).putExtra("type","Doctor");
                startActivity(doctor);
                finish();
            }
        });

        tuitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atuitor=new Intent(getApplicationContext(),owner.class).putExtra("type","Tutor");
                startActivity(atuitor);
                finish();
            }
        });


    }

}
