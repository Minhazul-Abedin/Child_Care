package com.example.child_care.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.child_care.Models.Child;
import com.example.child_care.R;
import com.example.child_care.TutorUpdateActivity;

import java.util.ArrayList;

public class ChildListForTutorAdapter extends RecyclerView.Adapter<ChildListForTutorAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Child> childs;

    public ChildListForTutorAdapter(Context mContext, ArrayList<Child> childs) {
        this.mContext = mContext;
        this.childs = childs;
    }

    @NonNull
    @Override
    public ChildListForTutorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.child_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildListForTutorAdapter.MyViewHolder myViewHolder, int i) {

        final int position = i;

        myViewHolder.name.setText(childs.get(i).getName());
        myViewHolder.age.setText(childs.get(i).getAge());

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mContext.startActivity(new Intent(mContext, TutorUpdateActivity.class)
                        .putExtra("name",childs.get(position).getName())
                        .putExtra("age",childs.get(position).getAge()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return childs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,age;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_child);
            age  = itemView.findViewById(R.id.age_child);
            cardView = itemView.findViewById(R.id.cardItem_child);
        }
    }
}

