package com.example.ryanforgie.javalistproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Plan plan  = new Plan();
        ArrayList<Run> week = plan.getWeek();

        Tracker tracker = new Tracker();

        PlanAdapter planAdapter = new PlanAdapter(this, week, tracker);

        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);



        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(Integer.toString(tracker.getCount()));
//
//        View button = findViewById(R.id.toggleButton);
//        button.setTag(tracker);


    }

    public void getRun(View listItem) {
        Run selectedRun = (Run) listItem.getTag();

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra("runToShow", selectedRun.getType().toString());

        startActivity(intent);
    }



}
