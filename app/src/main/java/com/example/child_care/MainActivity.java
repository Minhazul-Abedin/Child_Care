package com.example.child_care;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   Button doc,parents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doc=(Button)findViewById(R.id.doc);
        parents=(Button)findViewById(R.id.parents);

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doci=new Intent(MainActivity.this,Options.class);
                startActivity(doci);
            }
        });

        parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pa=new Intent(MainActivity.this,parents.class);
                startActivity(pa);
            }
        });





    }
}
