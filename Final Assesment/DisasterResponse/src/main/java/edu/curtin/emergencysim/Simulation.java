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
import java.util.regex.*;

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

     // A regular expression for validating and extracting parts of outgoing ('send') messages.
     private static final Pattern SEND_REGEX = Pattern.compile(
        "(?<emergency>fire|flood|chemical) ((?<status>start|end|low|high)|(?<lossType>casualty|damage|contam) (?<lossCount>[0-9]+)) (?<location>.+)");


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
            newEvents = rci.poll(); //poll() call
            if(!newEvents.isEmpty()) //if poll list is not empty
            {
                for (String s : newEvents) {
                    System.out.println(s); //TODO: Should do more with this

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
                        rci.send(createSendMessage(nxt)); //sends events to responder
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

    //TODO: INCOMPLETE
    public void displayMessage(String s) throws IllegalArgumentException
    {
        Matcher m = SEND_REGEX.matcher(s);
        if(!m.matches())
        {
            throw new IllegalArgumentException("Invalid message format: '" + s + "'");
        }
        String emergency = m.group("emergency");
        String status = m.group("status");
        String lossType = m.group("lossType");
        String lossCount = m.group("lossCount");
        String location = m.group("location");

        System.out.printf("%s at %s: ", emergency, location);
        if(status != null)
        {
            System.out.println(status);
        }
        else
        {
            System.out.printf("%s #%s", lossType, lossCount);
        }

    }


    /************************************************************
    IMPORT: nxt (Event)
    EXPORT: outStr (String)
    Creates outgoing message and validates it.
    ************************************************************/
    public String createSendMessage(Event nxt) throws IllegalArgumentException
    {
        String outStr;
        switch (nxt.getEmergencyType())
        {
            case FIRE:
                outStr = "fire low"+ nxt.getLocation(); //fire always starts at low intensity

                break;

            case FLOOD:
                outStr = "flood start " + nxt.getLocation();

                break;

            case CHEMICAL:
                outStr = "chemical start " + nxt.getLocation();

                break;

            default:
                throw new IllegalArgumentException("Invalid Emergency type: '" + nxt.getEmergencyType() + "'");
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
