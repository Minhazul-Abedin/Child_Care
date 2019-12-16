package com.example.child_care;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class child_show extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_show);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Baby");
        mDatabase.keepSynced(true);
        recyclerView=(RecyclerView)findViewById(R.id.myrecycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<childraw, child_show.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<childraw, child_show.BlogViewHolder>
                (childraw.class,R.layout.child_row, child_show.BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(child_show.BlogViewHolder viewHolder, childraw model, int i) {

                viewHolder.setName(model.getName());
                viewHolder.setAge(model.getAge());
                viewHolder.setBirthday(model.getBirthday());
                viewHolder.setBlood(model.getBlood());



            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mview;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mview=itemView;

        }

        public void setName(String name){
            TextView child_name=(TextView)mview.findViewById(R.id.child_name);
            child_name.setText(name);

        }
        public void setAge(String age){
            TextView child_age=(TextView)mview.findViewById(R.id.child_age);
            child_age.setText(age);

        }

        public void setBirthday(String birthday){
            TextView child_birthday=(TextView)mview.findViewById(R.id.child_birthday);
            child_birthday.setText(birthday);

        }

        public void setBlood(String blood){
            TextView child_blood=(TextView)mview.findViewById(R.id.child_bgroup);
            child_blood.setText(blood);

        }


    }


}
