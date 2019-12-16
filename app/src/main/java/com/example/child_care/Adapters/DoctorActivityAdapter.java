package com.example.child_care.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.child_care.Models.DoctorUpdate;
import com.example.child_care.R;

import java.util.ArrayList;

public class DoctorActivityAdapter extends RecyclerView.Adapter<DoctorActivityAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<DoctorUpdate> childs;

    public DoctorActivityAdapter(Context mContext, ArrayList<DoctorUpdate> childs) {
        this.mContext = mContext;
        this.childs = childs;
    }

    @NonNull
    @Override
    public DoctorActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DoctorActivityAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.child_activity_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorActivityAdapter.MyViewHolder myViewHolder, int i) {

        final int position = i;

        myViewHolder.name.setText(childs.get(i).getName());
        myViewHolder.age.setText(childs.get(i).getAge());
        myViewHolder.blood.setText(childs.get(i).getBloodGroup());
        myViewHolder.health.setText(childs.get(i).getHealthCondition());
        myViewHolder.bmi.setText(childs.get(i).getBmi());


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
