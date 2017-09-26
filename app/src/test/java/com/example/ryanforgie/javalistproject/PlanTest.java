package com.example.ryanforgie.javalistproject;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * Created by ryanforgie on 22/09/2017.
 */
public class PlanTest {

    Run run;
    Plan plan;

    @Before
    public void before() {
        plan = new Plan();
    }

    @Test
    public void testWeekListStartsPopulated() {
        assertEquals(7, plan.getWeek().size());
    }

    @Test
    public void testWeekPopulatedWithRuns() {
        assertEquals(Type.REST, plan.getWeek().get(0).getType());
    }

}