/**
 * Simulation.java
 * OBSERVER
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

import edu.curtin.emergencysim.events.*;
import edu.curtin.emergencysim.notifier.*;
import edu.curtin.emergencysim.responders.*;

public class Simulation
{
    private EventNotifier<Event> en;
    private ResponderComm rci;
    private List<Event> activeEvents;

     /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    /************************************************************
     * Constructor: creates new RNG and RCI
     * @param fileName
     * @throws IOException
    ************************************************************/
    public Simulation(EventNotifier<Event> inEn, ResponderComm inRci)
    {
        en = inEn;
        rci = inRci; //if clock desyncs move this to run()
        activeEvents = new ArrayList<>();
        //System.out.println("TEST"); for(Event e : en.getEventQueue()){System.out.println(e.toString());} //DEBUG - Prints event queue
    }

    /************************************************************
     * outgoing message and validates it.
     * @throws InterruptedException
     ************************************************************/
    public void run() throws InterruptedException
    {

        boolean simIsActive = true;
        int seconds = 0; //timer

        System.out.println("Starting Simulation...");

        while (simIsActive) {

            simIsActive = poll(simIsActive);
            send(seconds); //sends message to responders, adds to active event list

            Thread.sleep(1000); //sleeps for 1 second
            seconds++;

            System.out.println("[t="+seconds+"]"); //DEBUG: prints seconds
            //System.out.println("TEST - Active Events: {"); for(Event e : activeEvents.values()){System.out.println(e.toString());}

            if(LOGR.isLoggable(Level.INFO)){ LOGR.info(seconds + "s"); } //LOGGER: time passed LVL=INFO
        }

        System.out.println("End of Simulation.");

    }

    /************************************************************
     * gets message from responders and formats the prints; clocktick
     * TODO: need to get responders arrival status state pattern?
     * @param simIsActive
     * @param activeEvents
     * @return
     ************************************************************/
    private boolean poll(boolean simIsActive) {
        List<String> messageList;

        messageList = rci.poll(); //gets latest message list from Responders
        if(!messageList.isEmpty()) //if message list is not empty
        {
            for (String s : messageList) {
                if(s.equals("end")) //checks for end message
                {
                    simIsActive = false; //end simulation
                }
                else
                {
                    try
                    {
                        en.receive(s); //Prints message
                        //TODO: need to get responders arrival status state pattern?
                        simClockTick(); //reduce timer on every active event by 1
                    }
                    catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
                }
            }
        }
        return simIsActive;
    }

    /************************************************************
     * checks event queue for any events scheduled to start this second
     * @param seconds
     * @param activeEvents
     ************************************************************/
    private void send(int seconds) {
        for (Event nxt : en.getEventQueue()) {
            if(nxt.getStartTime() == seconds) //if there any events scheduled to start this second
            {
                try {
                    //if(activeEvents.containsKey(nxt.getKey()))
                    activeEvents.add(nxt); //add event to activeList
                    rci.send(en.notify(nxt)); //sends events to responder - prints
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /************************************************************
    @param active (Map<String, Event>)
    Iterates through active event map and ticks the clock down on each
    also removes events which are over
    TODO: Clock tick needs to only happen if arrival status = true
    ************************************************************/
    private void simClockTick()
    {
        Event temp = null;
        boolean needsRemoval = false;
        for (Event event : activeEvents)
        {
            if(event.getCleanupRemaining() > 0)//if not over
            {
                event.clockTick();
            }
            else
            {
                System.out.println(event.toString() + " is over");
                temp = event;
                needsRemoval = true;
            }
        }
        if(needsRemoval)
        {
            activeEvents.remove(temp); //removes event from active list
        }

    }
}
