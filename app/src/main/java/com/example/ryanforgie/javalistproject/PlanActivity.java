package com.example.ryanforgie.javalistproject;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    TextView counterView;
    Button resetButton;
    PlanAdapter planAdapter;
    Tracker tracker;
    ArrayList<Run> week;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        this.setUpSharePreferences();

        counterView = (TextView) findViewById(R.id.counter_view);
        counterView.setText(Integer.toString(tracker.getCount()));
        resetButton = (Button) findViewById(R.id.reset_button);

        planAdapter = new PlanAdapter(this, week, tracker);
        ListView listView = (ListView) findViewById(R.id.week);
        listView.setAdapter(planAdapter);

    }

    // Menu bar methods

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemId = item.getItemId();
        switch (selectedItemId) {
            case R.id.action_settings:
                goToSettings();
                return true;
            case R.id.action_instructions:
                goToInstructions();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void goToInstructions() {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    // Action when you click list item

    public void getRun(View listItem) {
        Run selectedRun = (Run) listItem.getTag();

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra("runToShowType", selectedRun.getType().toString());
        intent.putExtra("runToShowNotes", selectedRun.getNotes());
        intent.putExtra("runToShowId", selectedRun.getId());

        startActivity(intent);
    }

    // Reset button - should be refactored in the future, too much functionality

    public void onResetButtonClicked(View button) {
        checkIfDisplayStreakToast();
        Gson gson = new Gson();
        for (Run run : week) {
            if (!run.checkCompleted()) {
                tracker.setCount(0);
                saveCounter();
                counterView.setText(String.valueOf(tracker.getCount()));
                checkLostStreak();

            }
            else if(run.checkCompleted()){
                run.setStatus(false);
                saveCounter();
                counterView.setText(String.valueOf(tracker.getCount()));
            }
        }
        sharedPreferences.edit()
                .putString("week", gson.toJson(week))
                .apply();

        planAdapter.notifyDataSetChanged();

    }

    // Function that saves the counter

    public void saveCounter() {
        Log.d("PlanActicity:", "Saving Counter" + String.valueOf(tracker.getCount()));
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putInt("counter", tracker.getCount())
                .apply();

    }


    // Toast functions

    public void checkIfDisplayStreakToast(){
        if ((tracker.getCount() % 7 == 0) && (tracker.getCount() != 0)) {
            makeToast("Week Streak", Toast.LENGTH_SHORT);
        }
    }

    public void checkLostStreak() {
        makeToast("Streak Lost", Toast.LENGTH_SHORT);

    }

    public void makeToast(String message, int length) {
        Toast toast = Toast.makeText(this, message, length);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    // Shared preferences helper - needs refactored in future

    public void setUpSharePreferences(){
        Plan plan  = new Plan();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
        Integer counter = sharedPreferences.getInt("counter", 0);
        Log.d("Counter:", String.valueOf(counter));
        Gson gson = new Gson();
        TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
        week = gson.fromJson(weekJson, runArrayTypeToken.getType());

        if(week.size() == 0){
            week = plan.getWeek();
            sharedPreferences.edit()
                    .putString("week", gson.toJson(week))
                    .apply();
        }
        tracker = new Tracker(counter);
    }
}

