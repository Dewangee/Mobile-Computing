package com.example.dewangee.quiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends Fragment {
    String question_name;
    RadioGroup rg;
    Button savebtn;
    RadioButton rb;
    int id;

    public Detail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View vi = inflater.inflate(R.layout.fragment_detail, container, false);


        rg = (RadioGroup) vi.findViewById(R.id.radioGroup);
        savebtn = (Button) vi.findViewById(R.id.bsave);
        rg.setVisibility(vi.INVISIBLE);
        savebtn.setVisibility(vi.INVISIBLE);
        final Bundle b = this.getArguments();
        final DatabaseHelper db = new DatabaseHelper(getContext());


        if (b != null) {
            question_name = b.getString("Name");
            id = b.getInt("ID");

            rg.setVisibility(vi.VISIBLE);
            savebtn.setVisibility(vi.VISIBLE);

            TextView name = (TextView) vi.findViewById(R.id.question_stmt);
            name.setText(question_name);

            if (db.getUserAnswer(id)!=null){
                if(db.getUserAnswer(id).equalsIgnoreCase("True")){
                    RadioButton truebtn = (RadioButton)vi.findViewById(R.id.truebtn);
                    truebtn.setChecked(true);
                }
                else if(db.getUserAnswer(id).equalsIgnoreCase("False")){
                    RadioButton falsebtn = (RadioButton)vi.findViewById(R.id.falsebtn);
                    falsebtn.setChecked(true);
                }
            }


            savebtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int selected = rg.getCheckedRadioButtonId();
                    if (selected != -1) {
                        rb = (RadioButton) vi.findViewById(selected);
                        db.update(id,rb.getText().toString() );
                        Toast.makeText(getContext(), "Selected :" + rb.getText(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }


        return vi;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
