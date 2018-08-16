package com.example.dewangee.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home_A1_2016034 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__a1_2016034);

        Log.i("lifecycle","State of the MainActivity is Created");
        Toast.makeText(this, "MainActivity is created",  Toast.LENGTH_SHORT).show();

        Button submit = findViewById(R.id.submit);
        Button clear = findViewById(R.id.clear);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameet = (EditText)findViewById(R.id.NameET);
                EditText rollet = (EditText)findViewById(R.id.RollET);
                EditText branchet = (EditText)findViewById(R.id.BranchET);
                EditText course1et = (EditText)findViewById(R.id.CoursesET1);
                EditText course2et = (EditText)findViewById(R.id.CoursesET2);
                EditText course3et = (EditText)findViewById(R.id.CoursesET3);
                EditText course4et = (EditText)findViewById(R.id.CoursesET4);

                String namee = nameet.getText().toString();
                String rolle = rollet.getText().toString();
                String branche = branchet.getText().toString();
                String course1e = course1et.getText().toString();
                String course2e = course2et.getText().toString();
                String course3e = course3et.getText().toString();
                String course4e = course4et.getText().toString();


                Intent intent = new Intent(Home_A1_2016034.this, Submit_A1_2016034.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", namee);
                bundle.putString("roll", rolle);
                bundle.putString("branch", branche);
                bundle.putString("course1", course1e);
                bundle.putString("course2", course2e);
                bundle.putString("course3", course3e);
                bundle.putString("course4", course4e);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = (EditText)findViewById(R.id.NameET);
                EditText roll = (EditText)findViewById(R.id.RollET);
                EditText branch = (EditText)findViewById(R.id.BranchET);
                EditText course1 = (EditText)findViewById(R.id.CoursesET1);
                EditText course2 = (EditText)findViewById(R.id.CoursesET2);
                EditText course3 = (EditText)findViewById(R.id.CoursesET3);
                EditText course4 = (EditText)findViewById(R.id.CoursesET4);


                name.setText("");
                roll.setText("");
                branch.setText("");
                course1.setText("");
                course2.setText("");
                course3.setText("");
                course4.setText("");


            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle","State of the MainActivity is Started");
        Toast.makeText(this, "MainActivity is started",  Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","State of the MainActivity is Resumed");
        Toast.makeText(this, "MainActivity is resumed",  Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle","State of the MainActivity is Paused");
        Toast.makeText(this, "MainActivity is paused",  Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","State of the MainActivity is Stopped");
        Toast.makeText(this, "MainActivity is stopped",  Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","State of the MainActivity is Destroyed");
        Toast.makeText(this, "MainActivity is destroyed",  Toast.LENGTH_SHORT).show();
    }
}
