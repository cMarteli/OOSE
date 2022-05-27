/**
 * UnitTest.java
 * Testing Harness
 * 2022/OOSE Assignment 2
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

import static edu.curtin.emergencysim.Colours.*; //imports GFX class

import edu.curtin.emergencysim.events.*;
// import edu.curtin.emergencysim.notifier.*;
// import edu.curtin.emergencysim.responders.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.*;

public class UnitTest
{
    public static final String INPUT_FILE = "input2.txt";
    //initializes classes for tests
    // EventNotifier<Event> en;
    // ResponderComm rci;
    // Simulation sim;

    // public UnitTest()
    // {
    //     FileIO<Event> fio = new FileIO<>(); //creates new file IO object that uses event
    //     en = new EventNotifierImpl(); //Event notifier class
    //     fio.readFile(INPUT_FILE, en);
    //     rci = new ResponderCommImpl();
    // }

    @Test
    public void testEvent() {
        //Simulation sim = new Simulation(INPUT_FILE);
        Event e1 = new Event(6, "FIRE", "Midtown");
        Event e2 = new Event(8, "FIRE", "Midtown");
        Event e3 = new Event(69, "FLOOD", "Midtown");

        //Fire Midtown both events, should be true
        assertTrue(e1.isSame(e2));
        assertTrue(e1.compare("FIRE", "Midtown"));
        assertTrue(e1.compare("fire", "Midtown"));
        //Flood != fire Should be false
        assertFalse(e1.isSame(e3));
        assertFalse(e1.compare("flood", "Midtown"));
        assertFalse(e1.compare("FIRE", "Anywhere"));
    }

    @Test
    public void testEventNotifier()
    {
        en = new EventNotifierImpl();
        en.addEvent(24, "FLOOD", "Midtown");
        en.addEvent(6, "FIRE", "Midtown");
        en.addEvent(12, "FIRE", "Hill Valley");
        en.addEvent(24, "FLOOD", "Hill Valley");

        //assertEquals(30, )
    }

    // @Test
    // public void testSimulation()
    // {
    //     fio.readFile(INPUT_FILE, en);
    //     rci = new ResponderCommImpl();
    //     sim = new Simulation(en, rci);

    // }


}
