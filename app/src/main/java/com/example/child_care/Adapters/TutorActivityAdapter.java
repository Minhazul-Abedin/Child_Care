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
import com.example.child_care.Models.DoctorUpdate;
import com.example.child_care.Models.TutorUpdate;
import com.example.child_care.R;
import com.example.child_care.TutorUpdateActivity;

import java.util.ArrayList;

public class TutorActivityAdapter extends RecyclerView.Adapter<TutorActivityAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<TutorUpdate> childs;

    public TutorActivityAdapter(Context mContext, ArrayList<TutorUpdate> childs) {
        this.mContext = mContext;
        this.childs = childs;
    }

    @NonNull
    @Override
    public TutorActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.child_activity_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TutorActivityAdapter.MyViewHolder myViewHolder, int i) {

        final int position = i;

        myViewHolder.name.setText(childs.get(i).getName());
        myViewHolder.age.setText(childs.get(i).getAge());
        myViewHolder.blood.setText(childs.get(i).getHomeWork());
        myViewHolder.health.setText(childs.get(i).getStudyTime());


    }

    @Override
    public int getItemCount() {
        return childs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,age,blood,health,bmi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_activity);
            age  = itemView.findViewById(R.id.age_activity);
            blood  = itemView.findViewById(R.id.blood_activity);
            health  = itemView.findViewById(R.id.condition_activity);
            bmi  = itemView.findViewById(R.id.bmi_activity);
        }
    }
}
