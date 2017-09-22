package com.example.ryanforgie.javalistproject;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Plan {

    private ArrayList<Run> week;

    public Plan() {
        week = new ArrayList<Run>();
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
        week.add(new Run(Type.REST, 0));
    }

    public ArrayList<Run> getWeek() {
        return new ArrayList<Run>(week);
    }
}
