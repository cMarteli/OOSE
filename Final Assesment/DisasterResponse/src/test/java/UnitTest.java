/**
 * UnitTest.java
 * Testing Harness
 * 2022/OOSE Assignment 2
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

import edu.curtin.emergencysim.events.*;
import edu.curtin.emergencysim.notifier.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.*;

public class UnitTest
{
    //public static final String INPUT_FILE = "input2.txt";
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

    //@Test
    public void testEvent() {
        Event[] e = new Event[3];
        e[0] = new Event(6, "FIRE", "Midtown");
        e[1] = new Event(8, "FIRE", "Midtown");
        e[2] = new Event(69, "FLOOD", "Midtown");

        assertTrue(e[0].compare("FIRE", "Midtown"));
        assertTrue(e[0].compare("fire", "Midtown"));
        //Flood != fire Should be false
        assertFalse(e[0].compare("flood", "Midtown"));
        assertFalse(e[0].compare("FIRE", "Anywhere"));

        for (int i = 0; i < e.length; i++) {
        // sanity check for probability
            for (int j = 0; j < 100; j++) { //test each 100 times
                assertFalse(e[i].roll(0.00)); //0 chance should always be false
                assertTrue(e[i].roll(1.0)); //max chance should always be true
            }
        }

    }

    //@Test
    public void testEventNotifier()
    {
        EventNotifier en = new EventNotifierImpl();
        en.addEvent(24, "FLOOD", "Midtown");
        en.addEvent(6, "FIRE", "Midtown");
        en.addEvent(12, "FIRE", "Hill Valley");
        en.addEvent(24, "FLOOD", "Hill Valley");

        assertFalse(en.checkDupes("FLOOD", "Anywhere"));
        assertTrue(en.checkDupes("FLOOD", "Hill Valley"));
    }

    // @Test
    // public void testSimulation()
    // {
    //     fio.readFile(INPUT_FILE, en);
    //     rci = new ResponderCommImpl();
    //     sim = new Simulation(en, rci);

    // }

    @Test
    public void testFireLow()
    {
        System.out.println("Testing low fire:");
        Event fl = new Event(5, "FIRE", "Midtown");
        //casualty
        for (int i = 0; i < 1000; i++) {
            fl.checkCasualty();
        }
        System.out.println("Casualty count: " + fl.getCasualtyCount());
        //damage
        for (int i = 0; i < 1000; i++) {
            fl.checkDamage();
        }
        System.out.println("Damage count: " + fl.getDmgCount());
    }

    @Test
    public void testFireHigh()
    {
        System.out.println("Testing high fire:");
        Event fh = new Event(5, "FIRE", "Midtown");

        fh.intensityChange(); //changes to high here
        //casualty
        for (int i = 0; i < 1000; i++) {
            fh.checkCasualty();
        }
        System.out.println("Casualty count: " + fh.getCasualtyCount());
        //damage
        for (int i = 0; i < 1000; i++) {
            fh.checkDamage();
        }
        System.out.println("Damage count: " + fh.getDmgCount());
    }

    @Test
    public void testLowToHighFire()
    {
        System.out.println("Testing low-high fire:");
        Event f = new Event(5, "FIRE", "Midtown");

        //casualty - low
        for (int i = 0; i < 500; i++) {
            f.checkCasualty();
        }
        //damage - low
        for (int i = 0; i < 500; i++) {
            f.checkDamage();
        }
        f.intensityChange(); //changes to high here
        //casualty - high
        for (int i = 0; i < 500; i++) {
            f.checkCasualty();
        }
        //damage - high
        for (int i = 0; i < 500; i++) {
            f.checkDamage();
        }
        System.out.println("Casualty count: " + f.getCasualtyCount());
        System.out.println("Damage count: " + f.getDmgCount());

    }



}
