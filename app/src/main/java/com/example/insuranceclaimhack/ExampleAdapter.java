package com.example.insuranceclaimhack;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView claimName;
        public TextView claimDesc;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            claimName = itemView.findViewById(R.id.claimName);
            claimDesc = itemView.findViewById(R.id.claimDesc);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.name.setText(currentItem.getName());
        holder.claimName.setText(currentItem.getClaimName());
        holder.claimDesc.setText(currentItem.getClaimDesc());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }}
