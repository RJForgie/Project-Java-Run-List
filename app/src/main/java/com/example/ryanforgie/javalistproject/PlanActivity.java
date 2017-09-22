package com.example.ryanforgie.javalistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    TextView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Plan plan  = new Plan();
        ArrayList<Run> week = plan.getWeek();

        PlanAdapter planAdapter = new PlanAdapter(this, week);

        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);

        Tracker tracker = new Tracker();

        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(Integer.toString(tracker.getCount()));

    }

    ToggleButton toggle = (ToggleButton) findViewById(R.id.togglebutton);
    toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // The toggle is enabled
            } else {
                // The toggle is disabled
            }
        }
    });

}
