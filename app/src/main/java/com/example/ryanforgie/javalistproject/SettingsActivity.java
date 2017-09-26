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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ArrayList<Run> week;
    ArrayList<EditText> editTexts;
    Button saveButton;
    EditText firstRunDistance;
    EditText secondRunDistance;
    EditText thirdRunDistance;
    EditText fourthRunDistance;
    EditText fifthRunDistance;
    EditText sixthRunDistance;
    EditText seventhRunDistance;
    Spinner firstRunType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTexts = new ArrayList<>();
        this.setUpEditTextArray();
        this.populateSpinner;


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
        Gson gson = new Gson();
        TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
        week = gson.fromJson(weekJson, runArrayTypeToken.getType());

        saveButton = (Button) findViewById(R.id.save_button);
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

    public void populateSpinner(){
        categories = new ArrayList<>();
        for (Category category: Category.values()){
            String inputString = removeUnderscore(category.toString());
            StringBuilder categoryCapitalized = new StringBuilder(inputString.toLowerCase());
            categoryCapitalized.setCharAt(0, Character.toUpperCase(categoryCapitalized.charAt(0)));
            categories.add(categoryCapitalized.toString());
        }

        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category_spinner.setAdapter(categoryAdapter);
    }

    public void onSaveButtonClicked(View button) {
        Gson gson = new Gson();
        for (int i = 0;i<week.size();i++) {


            String value = editTexts.get(i).getText().toString();
            int integerValue = Integer.parseInt(value);
            week.get(i).setDistance(integerValue);
        }
        sharedPreferences.edit()
                .putString("week", gson.toJson(week))
                .apply();

    }

}
