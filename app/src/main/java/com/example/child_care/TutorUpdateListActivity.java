package com.example.child_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.child_care.Adapters.ChildListForDoctorAdapter;
import com.example.child_care.Adapters.TutorActivityAdapter;
import com.example.child_care.Models.Child;
import com.example.child_care.Models.TutorUpdate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TutorUpdateListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TutorUpdate> list;
    private TutorActivityAdapter mAdapter;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_update_list);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_tutor_update);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list = new ArrayList<>();

        mReference = FirebaseDatabase.getInstance().getReference().child("TutorUpdate");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    TutorUpdate tutorUpdate = dataSnapshot1.getValue(TutorUpdate.class);
                    list.add(tutorUpdate);

                }


                mAdapter = new TutorActivityAdapter(TutorUpdateListActivity.this,list);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}
