package com.example.ryanforgie.javalistproject;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Plan {

    private ArrayList<Run> week;

    public Plan() {
        week = new ArrayList<Run>();
        week.add(new Run(Type.REST, 1, true));
        week.add(new Run(Type.TEMPO, 2, false));
        week.add(new Run(Type.REST, 3, false));
        week.add(new Run(Type.REST, 4, false));
        week.add(new Run(Type.REST, 5, false));
        week.add(new Run(Type.REST, 6, false));
        week.add(new Run(Type.REST, 7, false));
    }

    public ArrayList<Run> getWeek() {
        return new ArrayList<Run>(week);
    }
}
