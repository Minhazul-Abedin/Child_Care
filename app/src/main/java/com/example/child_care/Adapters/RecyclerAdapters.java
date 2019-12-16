package com.example.child_care.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.child_care.Ownerr;
import com.example.child_care.R;

import java.util.ArrayList;

public class RecyclerAdapters extends RecyclerView.Adapter<RecyclerAdapters.MyViewHolder> {

    private Context mContext;
    private ArrayList<Ownerr> ownerrs;

    public RecyclerAdapters(Context mContext, ArrayList<Ownerr> ownerrs) {
        this.mContext = mContext;
        this.ownerrs = ownerrs;
    }

    @NonNull
    @Override
    public RecyclerAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.doc_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapters.MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(ownerrs.get(i).getName());
        myViewHolder.phone.setText(ownerrs.get(i).getPhone());
        myViewHolder.specialist.setText(ownerrs.get(i).getSpecialist());

    }

    @Override
    public int getItemCount() {
        return ownerrs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,phone,specialist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.doc_name);
            phone  = itemView.findViewById(R.id.doc_phone);
            specialist  = itemView.findViewById(R.id.doc_spe);
        }
    }
}