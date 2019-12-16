package com.example.child_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.child_care.Adapters.DoctorActivityAdapter;
import com.example.child_care.Adapters.TutorActivityAdapter;
import com.example.child_care.Models.DoctorUpdate;
import com.example.child_care.Models.TutorUpdate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorUpdateListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DoctorUpdate> list;
    private DoctorActivityAdapter mAdapter;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_list);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_doctor_update);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list = new ArrayList<>();

        mReference = FirebaseDatabase.getInstance().getReference().child("DoctorUpdate");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    DoctorUpdate doctorUpdate = dataSnapshot1.getValue(DoctorUpdate.class);
                    list.add(doctorUpdate);

                }


                mAdapter = new DoctorActivityAdapter(DoctorUpdateListActivity.this,list);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}
