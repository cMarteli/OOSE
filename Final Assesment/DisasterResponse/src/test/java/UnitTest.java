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

    @Test
    public void testEvent() {
        Event[] e = new Event[3];
        e[0] = new Event(6, "fire", "Midtown");
        e[1] = new Event(8, "fire", "Midtown");
        e[2] = new Event(69, "flood", "Midtown");

        assertTrue(e[0].compare("fire", "Midtown"));
        assertTrue(e[0].compare("fire", "Midtown"));
        //Flood != fire Should be false
        assertFalse(e[0].compare("flood", "Midtown"));
        assertFalse(e[0].compare("flood", "Anywhere"));

        for (Event event : e) {
            // sanity check for probability
            for (int j = 0; j < 100; j++) { //test each 100 times
                assertFalse(event.roll(0.00)); //0 chance should always be false
                assertTrue(event.roll(1.0)); //max chance should always be true
            }
        }

    }

    @Test
    public void testEventNotifier()
    {
        EventNotifier<Event> en = new EventNotifierImpl();
        en.addEvent(24, "flood", "Midtown");
        en.addEvent(6, "fire", "Midtown");
        en.addEvent(12, "fire", "Hill Valley");
        en.addEvent(24, "flood", "Hill Valley");

        assertFalse(en.checkDupes("flood", "Anywhere"));
        assertTrue(en.checkDupes("flood", "Hill Valley"));
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
        Event fl = new Event(5, "fire", "Midtown");
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
        Event fh = new Event(5, "fire", "Midtown");

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
        Event f = new Event(5, "fire", "Midtown");

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
