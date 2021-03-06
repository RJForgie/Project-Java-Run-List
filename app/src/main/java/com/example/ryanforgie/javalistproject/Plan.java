package com.example.ryanforgie.javalistproject;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Plan {

    private ArrayList<Run> week;

    public Plan() {
        week = new ArrayList<>();
        week.add(new Run(0, Type.REST, 1, false));
        week.add(new Run(1, Type.TEMPO, 2, false));
        week.add(new Run(2, Type.REST, 3, false));
        week.add(new Run(3, Type.REST, 4, false));
        week.add(new Run(4, Type.REST, 5, false));
        week.add(new Run(5, Type.REST, 6, false));
        week.add(new Run(6, Type.REST, 7, false));
    }

    public ArrayList<Run> getWeek() {
        return new ArrayList<>(week);
    }
}
