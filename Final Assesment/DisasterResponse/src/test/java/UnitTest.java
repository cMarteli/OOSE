/**
 * UnitTest.java
 * Testing Harness
 * 2022/OOSE Assignment 2
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.*;

public class UnitTest
{
    public static final String INPUT_FILE = "input2.txt";
    //initializes classes for tests

    @Test
    public void isSame() {
        //Simulation sim = new Simulation(INPUT_FILE);
        Event e1 = new Event(6, "FIRE", "Midtown");
        Event e2 = new Event(8, "FIRE", "Midtown");
        Event e3 = new Event(69, "FLOOD", "Midtown");

        //Fire Midtown both events, should be true
        assertTrue(e1.isSame(e2));
        assertTrue(e1.isSame("FIRE", "Midtown"));
        assertTrue(e1.isSame("fire", "Midtown"));
        //Flood != fire Should be false
        assertFalse(e1.isSame(e3));
        assertFalse(e1.isSame("flood", "Midtown"));
        assertFalse(e1.isSame("FIRE", "Anywhere"));
    }

    // @Test
    // public void getActEvent()
    // {
    //     EventNotifier en = new EventNotifierImpl();
    //     en.addEvent(24, "FLOOD", "Midtown");
    //     en.addEvent(6, "FIRE", "Midtown");
    //     en.addEvent(12, "FIRE", "Hill Valley");
    //     en.addEvent(24, "FLOOD", "Hill Valley");

    //     //assertEquals(30, )


    // }


}
