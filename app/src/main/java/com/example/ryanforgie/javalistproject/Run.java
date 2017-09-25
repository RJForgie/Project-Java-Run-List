package com.example.ryanforgie.javalistproject;

import java.io.Serializable;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Run implements Serializable {

    private int id;
    private Type type;
    private int distance;
    private boolean completed;
//    private  dayName;



    public Run(Integer id, Type type, Integer distance, boolean completed) {
        this.id = id;
        this.type = type;
        this.distance = distance;
        this.completed = completed;
    }

    public Enum getType() {
        return type;
    }

    public Integer getDistance() {
        return distance;
    }

    public boolean checkCompleted() {
        return completed;
    }

    public void setStatus(boolean state) {
        this.completed = state;
    }

    public void switchStatus(){
        this.completed = !this.completed;
    }

    public int getId() {
        return id;
    }
}
