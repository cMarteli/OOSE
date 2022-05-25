/**
 * Simulation.java
 * OBSERVER
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
//import static edu.curtin.emergencysim.Constants.*; //imports GFX class

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.*;

import edu.curtin.emergencysim.responders.*;

public class Simulation
{
    private EventNotifier<Event> en;
    private Random rand;
    private ResponderComm rci;

     /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    //  // A regular expression for validating and extracting parts of outgoing ('send') messages.
    //  private static final Pattern SEND_REGEX = Pattern.compile(
    //     "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");


    /************************************************************
    IMPORT: inEn (EventNotifier)
    Constructor: creates new RNG and RCI
    ************************************************************/
    public Simulation(String fileName) throws IOException
    {
        en = FileIO.readFile(fileName); //throws exception here and object is not constructed
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
            newEvents = rci.poll(); //poll() call
            if(!newEvents.isEmpty()) //if poll list is not empty
            {
                for (String s : newEvents) {
                    if(s.equals("end")) //checks for end condition
                    {
                        simIsActive = false;
                    }
                    else
                    {
                        try {
                            en.receive(s); //formats message TODO: Should do more with this
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }

            //send()
            for (Event nxt : queue) {
                if(nxt.getTime() == seconds) //checks if there any events scheduled to start this second
                {
                    try {
                        rci.send(en.notify(nxt)); //sends events to responder
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            //logs time passed
            if (LOGR.isLoggable(Level.INFO)) {
            LOGR.info(seconds + "s"); }
            System.out.println(seconds + "s"); //debug
            Thread.sleep(1000); //sleeps for 1 second
            seconds++;
        }

        System.out.println("End of Simulation.");

    }

    /************************************************************
    IMPORT: probability (double)
    EXPORT: boolean
    Generates random double to 2decimals given probability
    if r is lower or equal to probability returns true
    ************************************************************/
    public boolean roll(double prob)
    {
        double r = Math.floor(rand.nextDouble()*100) / 100;
        System.out.println("(" + r + "/" + prob + ")" ); //debug
        return (r <= prob);
    }
}
