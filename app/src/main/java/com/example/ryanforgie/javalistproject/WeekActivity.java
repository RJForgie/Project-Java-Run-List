package com.example.ryanforgie.javalistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Week week = new Week();
        ArrayList<Run> week = Week.getW();

        WeekAdapter runAdapter = new TopCitiesAdapter(this, week);

        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(cityAdapter);

    }

    public void getCity(View listItem){
        Run run = (Run) listItem.getTag();
    }

}
