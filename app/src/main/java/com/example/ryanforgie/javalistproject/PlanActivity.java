package com.example.ryanforgie.javalistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Plan plan  = new Plan();
        ArrayList<Run> week = plan.getWeek();

        PlanAdapter planAdapter = new PlanAdapter(this, week);

        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);


    }

}
