package com.example.ryanforgie.javalistproject;

import java.net.Inet4Address;

/**
 * Created by ryanforgie on 22/09/2017.
 */

public class Tracker {

    //class potentially redundant due to shared preferences, remove in future refactor

    private int count;

    public Tracker() {
        this.count = 0;
    }

    public Tracker(int count) {
        this.count = count;
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }
}
