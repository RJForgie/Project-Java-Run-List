package com.example.ryanforgie.javalistproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    TextView runType;
    TextView runDescription;
    EditText runNotes;
    SharedPreferences sharedPreferences;
    ArrayList<Run> week;
    int runToShowId;
    Button saveButton;
    String runToShowType;
    String runToShowNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.setUpSharedPreferences();


        //Set up views
        runType = (TextView) findViewById(R.id.run_type);
        runDescription = (TextView) findViewById(R.id.run_description);
        runNotes = (EditText) findViewById(R.id.notes_view);
        saveButton = (Button) findViewById(R.id.save_details_button);

        //Get information from intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        runToShowType = extras.getString("runToShowType");
        runToShowNotes = extras.getString("runToShowNotes");
        runToShowId = extras.getInt("runToShowId");

        runNotes.setText(runToShowNotes);
        runType.setText("Run Type:  " + runToShowType);

        this.getCorrectDescription();

    }

    // Save button

    public void onSaveButtonClicked(View button) {
        Gson gson = new Gson();
        String getNotes = runNotes.getText().toString();
        for (Run run: week) {
            if (run.getId() == runToShowId) {
                run.setNotes(getNotes);
            }
        }

        sharedPreferences.edit()
                .putString("week", gson.toJson(week))
                .apply();
        runNotes.setText(getNotes);
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    // Shared prefs helper

    public void setUpSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
        Gson gson = new Gson();
        TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
        week = gson.fromJson(weekJson, runArrayTypeToken.getType());
    }

    // Change to switch statement when possible

    public void getCorrectDescription() {
        if (runToShowType.equals("REST")) {
            runDescription.setText(getResources().getString(R.string.rest));
        } else if (runToShowType.equals("TEMPO")) {
            runDescription.setText(getResources().getString(R.string.tempo));

        } else if (runToShowType.equals("RECOVERY")) {
            runDescription.setText(getResources().getString(R.string.recovery));

        } else if (runToShowType.equals("BASE")) {
            runDescription.setText(getResources().getString(R.string.base));

        } else if (runToShowType.equals("LONG")) {
            runDescription.setText(getResources().getString(R.string.longrun));

        } else if (runToShowType.equals("PROGRESSION")) {
            runDescription.setText(getResources().getString(R.string.progression));

        } else if (runToShowType.equals("HILLREPEATS")) {
            runDescription.setText(getResources().getString(R.string.hillrepeats));

        } else if (runToShowType.equals("INTERVALS")) {
            runDescription.setText(getResources().getString(R.string.intervals));

        } else if (runToShowType.equals("FARTLEK")) {
            runDescription.setText(getResources().getString(R.string.fartlek));
        }
    }
}

