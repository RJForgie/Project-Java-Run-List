package com.example.ryanforgie.javalistproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    Tracker tracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Plan plan  = new Plan();
        ArrayList<Run> week = plan.getWeek();

        tracker = new Tracker();

        planAdapter = new PlanAdapter(this, week, tracker);


        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);



        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(Integer.toString(tracker.getCount()));

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

//        int savedCounter = sharedPrefs.getInt("counter", 0);
//
//        tracker.setCount(savedCounter);


    }

    public void getRun(View listItem) {
        Run selectedRun = (Run) listItem.getTag();

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra("runToShow", selectedRun.getType().toString());

        startActivity(intent);
    }

    public void onResetButtonClicked(View button) {
        int currentCount = tracker.getCount();
        for (ToggleButton toggle : planAdapter.toggles) {
            if (!toggle.isChecked()) {
                Log.d("lksajd", "hit the reset to 0");
                tracker.setCount(currentCount = 0);
                saveCounter();
                counterView.setText(String.valueOf(currentCount));
            }
            else if(toggle.isChecked()){
                tracker.setCount(currentCount + 1);
                toggle.setChecked(false);
                saveCounter();
                counterView.setText(String.valueOf(currentCount));
            }


        }



    }

    public void saveCounter() {

        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putInt("counter", tracker.getCount())
                .apply();

    }
}

//    public void onResetButtonClicked(View button) {
//        int currentCount = tracker.getCount();
//        for (ToggleButton toggle : planAdapter.toggles) {
//            if (toggle.isChecked()) {
//                tracker.setCount(currentCount + 1);
//                toggle.setChecked(false);
//            }
//            else if(!toggle.isChecked()){
//                tracker.setCount(0);
//            }
//
//        }
//
//
//    }

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
