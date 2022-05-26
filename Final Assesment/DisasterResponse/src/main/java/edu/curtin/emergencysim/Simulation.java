/**
 * Simulation.java
 * OBSERVER
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
//import static edu.curtin.emergencysim.Constants.*; //imports GFX class

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /************************************************************
    IMPORT: inEn (EventNotifier)
    Constructor: creates new RNG and RCI
    ************************************************************/
    public Simulation(String fileName) throws IOException
    {
        FileIO<Event> fio = new FileIO<Event>(); //creates new file IO object that uses event
        en = new EventNotifierImpl();

        fio.readFile(fileName, en); //throws exception here if object is not constructed
        rand = new Random();
        rci = new ResponderCommImpl(); //if clock desyncs move this to run()

        //System.out.println("TEST"); for(Event e : en.getEventQueue()){System.out.println(e.toString());} //TODO DEBUG - Prints event queue
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
        Map<String, Event> activeEvents = new HashMap<>(); //map key = emergency+location; references current events
        System.out.println("Starting Simulation...");

        while (simIsActive) {

            simIsActive = poll(simIsActive, activeEvents);
            send(seconds);

            Thread.sleep(1000); //sleeps for 1 second
            seconds++;

            System.out.println("[t="+seconds+"]"); //TODO: DEBUG prints seconds
            //System.out.println("TEST - Active Events: {"); for(Event e : activeEvents.values()){System.out.println(e.toString());}

            if(LOGR.isLoggable(Level.INFO)){ LOGR.info(seconds + "s"); } //LOGGER: time passed LVL=INFO
        }

        System.out.println("End of Simulation.");

    }

    /************************************************************
    IMPORT:
    EXPORT:
    poll()/receive()
    ************************************************************/
    private boolean poll(boolean simIsActive, Map<String, Event> activeEvents) {
        List<String> messageList;

        messageList = rci.poll(); //poll() call
        if(!messageList.isEmpty()) //if poll list is not empty
        {
            for (String s : messageList) {
                if(s.equals("end")) //checks for end condition
                {
                    simIsActive = false;
                }
                else
                {
                    try
                    {
                        en.receive(s); //TODO: Crashing
                        //clockTick(activeEvents); //reduce timer on active events every second
                    }
                    catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
                }
            }
        }
        return simIsActive;
    }

    /************************************************************
    IMPORT: seconds (int)
    EXPORT: none
    send()/notify()
    ************************************************************/
    private void send(int seconds) {
        for (Event nxt : en.getEventQueue()) {
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
    }

    /************************************************************
    IMPORT:
    EXPORT:
    clock tick
    ************************************************************/
    private void clockTick(Map<String, Event> active)
    {
        Event toRemove = null;
        boolean remove = false;
        for (Event event : active.values())
        {
            if(!event.isOver())//checks if already over
            {
                event.cleanupTick();
            }
            else
            {
                System.out.println(event.getEmergencyType() + " at " + event.getLocation() + " is over");
                toRemove = event;
                remove = true;
            }
        }
        if(remove)
        {
            active.remove(toRemove.getKey()); //removes event from active list
        }

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
