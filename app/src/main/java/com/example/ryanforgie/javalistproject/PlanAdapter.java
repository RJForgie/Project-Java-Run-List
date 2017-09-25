package com.example.ryanforgie.javalistproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class PlanAdapter extends ArrayAdapter<Run> {


    Tracker tracker;
    ArrayList<ToggleButton> toggles;
    ToggleButton toggle;




    public PlanAdapter(Context context, ArrayList<Run> week, Tracker tracker){
        super(context, 0, week);
        this.tracker = tracker;
    }

    @Override
    public View getView(final int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.run_item, parent, false);
        }

        final Run currentRun = getItem(position);

        TextView type = listItemView.findViewById(R.id.type);
        type.setText(currentRun.getType().toString());

        TextView distance = listItemView.findViewById(R.id.distance);
        distance.setText(currentRun.getDistance().toString());

        toggle = listItemView.findViewById(R.id.toggleButton);
        toggle.setChecked(currentRun.checkCompleted());

        toggle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AppCompatActivity planActivity = (AppCompatActivity) v.getContext();

                TextView counterView = (TextView) planActivity.findViewById(R.id.counter_view);
                Log.d("planadapter", "hitting the onclick");
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("run_list", Context.MODE_PRIVATE);
                String weekJson = sharedPreferences.getString("week", new ArrayList<Run>().toString());
                Gson gson = new Gson();
                TypeToken< ArrayList<Run> > runArrayTypeToken = new TypeToken<ArrayList<Run>>(){};
                ArrayList<Run> week = gson.fromJson(weekJson, runArrayTypeToken.getType());
                int id = currentRun.getId();

                currentRun.switchStatus();
                week.get(id).switchStatus();

                int currentCount = tracker.getCount();
                if(currentRun.checkCompleted() == true){
                    tracker.setCount(currentCount + 1);

                } else {
                    tracker.setCount(currentCount - 1);
                }
                saveCounter();
                Log.d("total", tracker.getCount().toString());
                counterView.setText(String.valueOf(tracker.getCount()));

                sharedPreferences.edit()
                        .putString("week", gson.toJson(week))
                        .apply();
            }
        });

//                if (isChecked) {
////                    tracker.setCount(currentCount + 1);
////                    counterView.setText(Integer.toString(tracker.getCount()));
////                    saveCounter();

////                    int currentCount = tracker.getCount();
////                    tracker.setCount(currentCount - 1);
////                    counterView.setText(Integer.toString(tracker.getCount()));
////                    saveCounter();


        listItemView.setTag(currentRun);

        return listItemView;

    }


    public void saveCounter() {

        PreferenceManager.getDefaultSharedPreferences(getContext())
                .edit()
                .putInt("counter", tracker.getCount())
                .apply();

    }

}


//        ImageView flag = (ImageView) listItemView.findViewById(R.id.flag);
//        flag.setImageResource(currentCity.getImageId());

