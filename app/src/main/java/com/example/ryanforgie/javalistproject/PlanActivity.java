package com.example.ryanforgie.javalistproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    TextView counterView;
    Button resetButton;
    PlanAdapter planAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);


        Plan plan  = new Plan();
        ArrayList<Run> week = plan.getWeek();

        Tracker tracker = new Tracker();

       planAdapter = new PlanAdapter(this, week, tracker);

        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);

        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(Integer.toString(tracker.getCount()));

    }

    public void getRun(View listItem) {
        Run selectedRun = (Run) listItem.getTag();

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra("runToShow", selectedRun.getType().toString());

        startActivity(intent);
    }

    public void onResetButtonClicked(View button) {
        for (ToggleButton toggle : planAdapter.toggles) {
            toggle.setChecked(false);
        }


    }
}

//
//        View button = findViewById(R.id.toggleButton);
//        button.setTag(tracker);

//        if (selectedRun.getType().toString().equals("REST")) {
//            intent.putExtra("runToShow", getResources().getString(R.string.easy));
//        }
//        else {
//            if (selectedRun.getType().toString().equals("TEMPO")) {
//                intent.putExtra("runToShow", getResources().getString(R.string.app_name));
//            }
//        }
