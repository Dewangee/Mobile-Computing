package com.example.dewangee.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Submit_A1_2016034 extends AppCompatActivity {

    String state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__a1_2016034);

        Log.i("lifecycle","State of the SubmitActivity is Created");
        Toast.makeText(this, "SubmitActivity is created",  Toast.LENGTH_SHORT).show();

        state = "Created";

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle !=null){
            String name = bundle.getString("name");
            String roll = bundle.getString("roll");
            String branch = bundle.getString("branch");
            String course1 = bundle.getString("course1");
            String course2 = bundle.getString("course2");
            String course3 = bundle.getString("course3");
            String course4 = bundle.getString("course4");

            TextView nametv = (TextView)findViewById(R.id.NameET);
            TextView rolltv = (TextView)findViewById(R.id.RollET);
            TextView branchtv = (TextView)findViewById(R.id.BranchET);
            TextView course1tv = (TextView)findViewById(R.id.CoursesET1);
            TextView course2tv = (TextView)findViewById(R.id.CoursesET2);
            TextView course3tv = (TextView)findViewById(R.id.CoursesET3);
            TextView course4tv = (TextView)findViewById(R.id.CoursesET4);

            nametv.setText(name);
            rolltv.setText(roll);
            branchtv.setText(branch);
            course1tv.setText(course1);
            course2tv.setText(course2);
            course3tv.setText(course3);
            course4tv.setText(course4);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle","State of the SubmitActivity is Started from " + state);
        Toast.makeText(this, "SubmitActivity is started",  Toast.LENGTH_SHORT).show();
        state = "Started";
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","State of the SubmitActivity is Resumed from "+state);
        Toast.makeText(this, "SubmitActivity is resumed",  Toast.LENGTH_SHORT).show();
        state = "Resumed";
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle","State of the SubmitActivity is Paused from "+ state );
        Toast.makeText(this, "SubmitActivity is paused",  Toast.LENGTH_SHORT).show();
        state = "Paused";
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","State of the SubmitActivity is Stopped from " + state );
        Toast.makeText(this, "SubmitActivity is stopped",  Toast.LENGTH_SHORT).show();
        state = "Stopped";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","State of the SubmitActivity is Destroyed from "+ state);
        Toast.makeText(this, "SubmitActivity is destroyed",  Toast.LENGTH_SHORT).show();
        state = "Destroyed";
    }



}
