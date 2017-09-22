package com.example.ryanforgie.javalistproject;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Run {

    private Type type;
    private int distance;

    public Run(Type type, int distance) {
        this.type = type;
        this.distance = distance;
    }

    public Enum getType() {
        return type;
    }

    public int getDistance() {
        return distance;
    }
}
