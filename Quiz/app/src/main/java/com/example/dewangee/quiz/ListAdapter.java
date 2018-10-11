package com.example.dewangee.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public ArrayList<Question> question_list ;
    public Context context;
    Interfacefrag interfacefrag;

    public ListAdapter(Interfacefrag interfacefrag){
       this.interfacefrag = interfacefrag;

    }

    public void setData(ArrayList<Question> arrayList){
        question_list= arrayList;
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder{
        Button ques_btn;

        ViewHolder(View view){
            super(view);
            ques_btn = (Button)view.findViewById(R.id.ques_btn);


        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Question question = question_list.get(i);
        viewHolder.ques_btn.setText("Question " + (i+1));
        final int id = question.getId();
        viewHolder.ques_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question_name = question.getStmt();
                Detail detail =new Detail();
                Bundle bundle=new Bundle();

                bundle.putString("key",question_name);
                bundle.putInt("keyint", id);
                detail.setArguments(bundle);
                interfacefrag.func(i,question_name, id);
            }
        });
    }

    @Override
    public int getItemCount() {

       return question_list.size();

    }


}
