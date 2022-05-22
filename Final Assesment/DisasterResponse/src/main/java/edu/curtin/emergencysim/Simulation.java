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
import java.util.logging.*;

import edu.curtin.emergencysim.responders.*;

public class Simulation
{
    private EventNotifier en;
    private Random rand;
    private ResponderComm rci;

     /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());


    /************************************************************
    IMPORT: inEn (EventNotifier)
    Constructor: creates new RNG and RCI
    ************************************************************/
    public Simulation(EventNotifier inEn)
    {
        en = inEn;
        rand = new Random();
        rci = new ResponderCommImpl(); //if clock desyncs move this to run()
    }

    /************************************************************
    IMPORT: nxt (Event)
    EXPORT: outStr (String)
    Creates outgoing message and validates it. Throws IllegalArgumentException()
    ************************************************************/
    public void run() throws InterruptedException
    {
        boolean simIsActive = true;
        int seconds = 0; //timer
        List<Event> queue = en.getEvents(); //gets event queue
        List<String> newEvents;
        System.out.println("Starting Simulation...");

        while (simIsActive) {

            //poll()
            newEvents = rci.poll(); //TODO: Should do more with this
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
            for (Event nxt : queue) {
                if(nxt.getTime() == seconds) //checks if there any events scheduled to start this second
                {
                    try {
                        rci.send(update(nxt)); //sends events to responder
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            if (LOGR.isLoggable(Level.INFO)) {
            LOGR.info(seconds + "s"); }
            //System.out.println(seconds + "s"); //debug
            Thread.sleep(1000); //sleeps for 1 second
            seconds++;
        }

        System.out.println("End of Simulation.");

    }

    /************************************************************
    IMPORT: nxt (Event)
    EXPORT: outStr (String)
    Creates outgoing message and validates it.
    ************************************************************/
    public String update(Event nxt) throws IllegalArgumentException
    {
        String outStr;
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
                throw new IllegalArgumentException("Invalid Emergency type: '" + nxt.getDis() + "'");
        }
        return outStr;
    }

    /************************************************************
    IMPORT: probability (double)
    EXPORT: boolean
    Generates random double to 2decimals given probability
    if r is lower or equal to probability returns true
    ************************************************************/
    public boolean roll(double probability)
    {
        double r = Math.floor(rand.nextDouble()*100) / 100;
        System.out.println("(" + r + "/" + probability + ")" ); //debug
        return (r <= probability);
    }
}
