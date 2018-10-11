package com.example.dewangee.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class List extends Fragment {

    public List() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vi = inflater.inflate(R.layout.fragment_list, container, false);

        MainActivity activity = (MainActivity) getActivity();
        ArrayList<Question> question_list = activity.getList();

        ListAdapter listAdapter = new ListAdapter( interfacefrag);


        RecyclerView list_rv = (RecyclerView) vi.findViewById(R.id.list_rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        list_rv.setLayoutManager(llm);

        list_rv.setAdapter(listAdapter);

        listAdapter.setData(question_list);
        return vi;
    }

    Interfacefrag interfacefrag = new Interfacefrag() {
        @Override
        public void func(int position, String name, int id) {


                Detail detail =new Detail();
                Bundle bundle1=new Bundle();
                bundle1.putString("Name",name);
                bundle1.putInt("ID", id);
                detail.setArguments(bundle1);
                FragmentManager manager=getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.detail_frag,detail);
                transaction.show(manager.findFragmentById(R.id.detail_frag)).commit();


        }
    };




}
