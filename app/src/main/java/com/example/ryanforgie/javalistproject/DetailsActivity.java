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

        runType.setText(runToShow);


    }


}
