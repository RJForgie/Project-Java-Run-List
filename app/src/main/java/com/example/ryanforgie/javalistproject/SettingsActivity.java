package com.example.ryanforgie.javalistproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<Run> week;
    ArrayList<EditText> editTexts;
    ArrayList<Spinner> spinners;
    ArrayList<String> types;
    ArrayAdapter<String> typeAdapter;
    Button saveButton;
    EditText firstRunDistance;
    EditText secondRunDistance;
    EditText thirdRunDistance;
    EditText fourthRunDistance;
    EditText fifthRunDistance;
    EditText sixthRunDistance;
    EditText seventhRunDistance;
    Spinner firstRunType;
    Spinner secondRunType;
    Spinner thirdRunType;
    Spinner fourthRunType;
    Spinner fifthRunType;
    Spinner sixthRunType;
    Spinner seventhRunType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.setUpSharedPreferences();
        editTexts = new ArrayList<>();
        spinners = new ArrayList<>();
        this.setUpEditTextArray();
        this.setDefaultsEditTextArray();
        this.setUpSpinnerViews();
        this.populateSpinner();
        this.setSpinnerDefaults();

        saveButton = (Button) findViewById(R.id.save_button);
    }

    public void setUpSpinnerViews() {
        firstRunType = (Spinner) findViewById(R.id.first_type_spinner);
        spinners.add(firstRunType);
        firstRunType.setSelection(3);
        secondRunType = (Spinner) findViewById(R.id.second_type_spinner);
        spinners.add(secondRunType);
        thirdRunType = (Spinner) findViewById(R.id.third_type_spinner);
        spinners.add(thirdRunType);
        fourthRunType = (Spinner) findViewById(R.id.fourth_type_spinner);
        spinners.add(fourthRunType);
        fifthRunType = (Spinner) findViewById(R.id.fifth_type_spinner);
        spinners.add(fifthRunType);
        sixthRunType = (Spinner) findViewById(R.id.sixth_type_spinner);
        spinners.add(sixthRunType);
        seventhRunType = (Spinner) findViewById(R.id.seventh_type_spinner);
        spinners.add(seventhRunType);

    }

    public void setUpEditTextArray(){
        firstRunDistance = (EditText) findViewById(R.id.first_run_distance);
        editTexts.add(firstRunDistance);
        secondRunDistance = (EditText) findViewById(R.id.second_run_distance);
        editTexts.add(secondRunDistance);
        thirdRunDistance = (EditText) findViewById(R.id.third_run_distance);
        editTexts.add(thirdRunDistance);
        fourthRunDistance = (EditText) findViewById(R.id.fourth_run_distance);
        editTexts.add(fourthRunDistance);
        fifthRunDistance = (EditText) findViewById(R.id.fifth_run_distance);
        editTexts.add(fifthRunDistance);
        sixthRunDistance = (EditText) findViewById(R.id.sixth_run_distance);
        editTexts.add(sixthRunDistance);
        seventhRunDistance = (EditText) findViewById(R.id.seventh_run_distance);
        editTexts.add(seventhRunDistance);
    }

    public void setDefaultsEditTextArray(){
        for (int i = 0; i < week.size(); i++) {
            editTexts.get(i).setText(week.get(i).getDistance().toString());
        }
    }

    public void populateSpinner(){
        types = new ArrayList<>();
        for (Type type: Type.values()){
            types.add(type.toString());
        }

        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < week.size(); i++) {
        spinners.get(i).setAdapter(typeAdapter);
        }
    }

    public void setSpinnerDefaults() {
            for (int i = 0; i < week.size(); i++) {
                int index = week.get(i).getType().ordinal();
                spinners.get(i).setSelection(index);
            }
    }

    public void onSaveButtonClicked(View button) {
        Gson gson = new Gson();
        updateDistancesLocally();
        updateTypesLocally();
        sharedPreferences.edit()
                .putString("week", gson.toJson(week))
                .apply();
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    public void updateDistancesLocally(){
        for (int i = 0;i<week.size();i++) {
            String value = editTexts.get(i).getText().toString();
            int integerValue = Integer.parseInt(value);
            week.get(i).setDistance(integerValue);
        }
    }

    public void updateTypesLocally() {
        for (int i = 0;i<week.size();i++) {
            String value = (String) spinners.get(i).getSelectedItem();
            Type type = Type.valueOf(value);
            week.get(i).setType(type);
        }
    }

    public void setUpSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
        Gson gson = new Gson();
        TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
        week = gson.fromJson(weekJson, runArrayTypeToken.getType());
    }

}
