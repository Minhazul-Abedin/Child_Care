package com.example.child_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.child_care.Adapters.ChildListForDoctorAdapter;
import com.example.child_care.Adapters.RecyclerAdapters;
import com.example.child_care.Models.Child;
import com.example.child_care.Models.DoctorUpdate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChildListForDoctorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Child> list;
    private ChildListForDoctorAdapter mAdapter;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list_for_doctor);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_doctor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        list = new ArrayList<>();

        mReference = FirebaseDatabase.getInstance().getReference().child("Baby");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Child child = dataSnapshot1.getValue(Child.class);
                    list.add(child);

                }


                mAdapter = new ChildListForDoctorAdapter(ChildListForDoctorActivity.this,list);
                Collections.sort(list, new Comparator<Child>() {
                    @Override
                    public int compare(Child o1, Child o2) {
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

    public void searchUsers(String str)
    {
        ArrayList<Child> myList = new ArrayList<>();
        for (Child obj : list)
        {
            if (obj.getName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(obj);
            }
        }

        ChildListForDoctorAdapter adapterClass = new ChildListForDoctorAdapter(ChildListForDoctorActivity.this,myList);
        recyclerView.setAdapter(adapterClass);
    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item  = menu.findItem(R.id.action_Search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchUsers(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
