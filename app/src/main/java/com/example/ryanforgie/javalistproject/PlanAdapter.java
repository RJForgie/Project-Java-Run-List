package com.example.ryanforgie.javalistproject;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class PlanAdapter extends ArrayAdapter<Run> {


    Tracker tracker;
    ArrayList<ToggleButton> toggles;


    public PlanAdapter(Context context, ArrayList<Run> week, Tracker tracker){
        super(context, 0, week);
        this.tracker = tracker;
        toggles = new ArrayList<ToggleButton>();
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.run_item, parent, false);
        }

        Log.d("count", tracker.getCount().toString());

        Run currentRun = getItem(position);

        TextView type = listItemView.findViewById(R.id.type);
        type.setText(currentRun.getType().toString());

        TextView distance = listItemView.findViewById(R.id.distance);
        distance.setText(currentRun.getDistance().toString());

        ToggleButton toggle = listItemView.findViewById(R.id.toggleButton);
        toggles.add(toggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                AppCompatActivity planActivity = (AppCompatActivity) buttonView.getContext();

                TextView counterView = (TextView) planActivity.findViewById(R.id.counter_view);

                if (isChecked) {
                    int currentCount = tracker.getCount();
                    tracker.setCount(currentCount + 1);
                    counterView.setText(Integer.toString(tracker.getCount()));

                } else {
                    int currentCount = tracker.getCount();
                    tracker.setCount(currentCount - 1);
                    counterView.setText(Integer.toString(tracker.getCount()));
                }
            }});


        listItemView.setTag(currentRun);

        return listItemView;

    }

}


//        ImageView flag = (ImageView) listItemView.findViewById(R.id.flag);
//        flag.setImageResource(currentCity.getImageId());

