package com.example.child_care;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.child_care.Adapters.RecyclerAdapters;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class authority extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private ArrayList<Ownerr> list;
    private RecyclerAdapters mAdapter;
    DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Authority");
        mDatabase.keepSynced(true);
        recyclerView=(RecyclerView)findViewById(R.id.myrecycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list = new ArrayList<>();

        mReference = FirebaseDatabase.getInstance().getReference().child("Authority");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Ownerr ownerr = dataSnapshot1.getValue(Ownerr.class);
                    assert ownerr != null;
                    if (ownerr.getType().equals("Owner")){
                        list.add(ownerr);
                    }
                }


                mAdapter = new RecyclerAdapters(authority.this,list);
                Collections.sort(list, new Comparator<Ownerr>() {
                    @Override
                    public int compare(Ownerr o1, Ownerr o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }


}
