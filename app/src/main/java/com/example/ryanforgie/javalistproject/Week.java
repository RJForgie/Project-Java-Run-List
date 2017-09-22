package com.example.ryanforgie.javalistproject;

import java.util.ArrayList;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Week {

    private ArrayList<Run> list;


    public Week(ArrayList<Run> list) {
        list = new ArrayList<Run>();
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
        list.add(new Run(Type.REST, 0));
    }

    public ArrayList<Run> getWeek() {
        return new ArrayList<Run>(list);
    }
}
