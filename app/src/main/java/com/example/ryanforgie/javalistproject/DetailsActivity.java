package com.example.ryanforgie.javalistproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView runType;
    TextView runDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        runType = (TextView) findViewById(R.id.run_type);
        runDescription = (TextView) findViewById(R.id.run_description);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String runToShow = extras.getString("runToShow");

        if (runToShow.equals("REST")) {
            runType.setText(getResources().getString(R.string.rest));
        } else if (runToShow.equals("TEMPO")) {
            runType.setText(getResources().getString(R.string.tempo));

        } else if (runToShow.equals("RECOVERY")) {
            runType.setText(getResources().getString(R.string.recovery));

        } else if (runToShow.equals("BASE")) {
            runType.setText(getResources().getString(R.string.base));

        } else if (runToShow.equals("LONG")) {
            runType.setText(getResources().getString(R.string.long_run));

        } else if (runToShow.equals("PROGRESSION")) {
            runType.setText(getResources().getString(R.string.progression_run));

        } else if (runToShow.equals("HILLREPEATS")) {
            runType.setText(getResources().getString(R.string.hill_repeats));

        } else if (runToShow.equals("INTERVALS")) {
            runType.setText(getResources().getString(R.string.intervals));

        }



    }


}

