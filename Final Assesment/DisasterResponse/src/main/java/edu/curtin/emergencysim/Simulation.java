/**
 * Simulation.java
 * OBSERVER
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
import static edu.curtin.emergencysim.Constants.*; //imports GFX class

import java.util.List;
import java.util.Random;

import edu.curtin.emergencysim.responders.*;

public class Simulation
{
    private EventNotifier en;
    private Random rand;
    ResponderComm rci;


    public Simulation(EventNotifier inEn)
    {
        en = inEn;
        rand = new Random();
        rci = new ResponderCommImpl(); //if clock desyncs move this to run()
    }

    public void run() throws InterruptedException
    {
        boolean simIsActive = true;
        int seconds = 0;
        System.out.println("Starting Simulation...");

        while (simIsActive) {
            //poll()
            List<String> newEvents = rci.poll();
            if(!newEvents.isEmpty()) //if poll list is not empty
            {
                for (String s : newEvents) {
                    System.out.println(s); //prints all poll messages
                    if(s.equals("end")) //checks for end condition
                    {
                        simIsActive = false;
                    }
                }
            }
            //send()
            try {
                List<Event> eList = en.getEvents();
                for (Event ev : eList) {
                    if(ev.getTime() == seconds) //checks if there any events scheduled to start that second
                    {
                        rci.send(update(ev)); //sends events to responder
                    }
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

            System.out.println(seconds + "s"); //DEBUG TODO
            Thread.sleep(1000); //sleeps for 1 second
            seconds++;
        }

        System.out.println("End of Simulation.");

    }

    public String update(Event nxt)
    {
        String outStr = "error!";
        switch (nxt.getDis())
        {
            case FIRE:
                String intensity = "low";

                if(roll(FIRE_HIGH_CASUALTY_PROB)) //TODO
                {
                    intensity = "high";
                }
                outStr = "fire " + intensity + nxt.getLocation();

                break;

            case FLOOD:
                outStr = "flood start " + nxt.getLocation();

                break;

            case CHEMICAL:
                outStr = "chemical start " + nxt.getLocation();

                break;

            default:
                break;
        }
        return outStr;
    }

    /************************************************************
    IMPORT: probability (double)
    EXPORT: boolean
    ASSERTION: Generates random double to 2decimals given probability
    if r is lower or equal to probability returns true
    ************************************************************/
    public boolean roll(double probability)
    {
        double r = Math.floor(rand.nextDouble()*100) / 100;
        System.out.println("(" + r + "/" + probability + ")" ); //debug
        return (r <= probability);
    }
}
