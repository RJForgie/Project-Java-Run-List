package com.example.ryanforgie.javalistproject;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * Created by ryanforgie on 22/09/2017.
 */
public class WeekTest {

    Run run;
    Week week;

    @Before
    public void before() {
        week = new Week();
    }

    @Test
    public void testWeekListStartsPopulated() {
        assertEquals(7, week.getWeek().size());
    }

    @Test
    public void testWeekPopulatedWithRuns() {
        assertEquals(Type.REST, week.getWeek().get(0).getType());

    }

}