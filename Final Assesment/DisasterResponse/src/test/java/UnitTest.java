/**
 * UnitTest.java
 * Testing Harness
 * 2022/OOSE Assignment 2
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

import edu.curtin.emergencysim.events.*;
//import edu.curtin.emergencysim.notifier.*;
import edu.curtin.emergencysim.responders.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.*;

public class UnitTest
{

    //@Test
    public void testEvent() {
        Event[] arr = new Event[3];
        arr[0] = new Event(6, "fire", "Midtown");
        arr[1] = new Event(8, "fire", "Midtown");
        arr[2] = new Event(69, "flood", "Midtown");

        assertTrue(arr[0].compare("fire", "Midtown"));
        assertTrue(arr[0].compare("fire", "Midtown"));
        //Flood != fire Should be false
        assertFalse(arr[0].compare("flood", "Midtown"));
        assertFalse(arr[0].compare("fire", "Anywhere"));

        for (Event event : arr) {
            for (int j = 0; j < 100; j++) { //test each 100 times
                assertFalse(event.roll(0.00)); //0 chance should always be false
                assertTrue(event.roll(1.0)); //max chance should always be true
            }
        }
    }


    @Test
    public void testSend()
    {
        ResponderComm rc = new ResponderCommImpl();
        System.out.println("\nTesting Send:");

        rc.send("fire damage 1 Perth");
        rc.send("fire damage 2 Perth");
        rc.send("chemical damage 5 Armadale");
        rc.send("chemical start Armadale");
        rc.send("fire start Armadale");


        System.out.println("\n***************");
    }

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
