package com.example.ryanforgie.javalistproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    TextView counterView;
    Button resetButton;
    PlanAdapter planAdapter;
    Tracker tracker;
    ArrayList<Run> week;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        Plan plan  = new Plan();

        // get week SP
        SharedPreferences sharedPreferences = getSharedPreferences("run_list", Context.MODE_PRIVATE);
        String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
        Gson gson = new Gson();
        TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
        week = gson.fromJson(weekJson, runArrayTypeToken.getType());


        // create week if empty in SP
        if(week.size() == 0){
            week = plan.getWeek();
            sharedPreferences.edit()
                    .putString("week", gson.toJson(week))
                    .apply();
        }

        tracker = new Tracker();
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
        int currentCount = tracker.getCount();
        for (Run run : week) {
            if (!run.checkCompleted()) {
                tracker.setCount(currentCount = 0);
                saveCounter();
                counterView.setText(String.valueOf(currentCount));
            }
            else if(run.checkCompleted()){
                tracker.setCount(currentCount + 1);
                run.setStatus(false);
                saveCounter();
                counterView.setText(String.valueOf(currentCount));
            }


        }
        planAdapter.notifyDataSetChanged();

//        finish();
//        startActivity(getIntent());


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
