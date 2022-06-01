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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.curtin.emergencysim.events.*;
import edu.curtin.emergencysim.notifier.*;
import edu.curtin.emergencysim.responders.*;

public class Simulation
{
    private EventNotifier<Event> en;
    private ResponderComm rci;
    private Map<String, Event> activeEvents;
    private List<Event> queue;

    // A regular expression for validating and extracting parts of outgoing ('send') messages.
    public static final Pattern SEND_REGEX = Pattern.compile(
        "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");
     /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    /************************************************************
     * Constructor: creates new RNG and RCI
     * @param fileName
     * @throws IOException
    ************************************************************/
    public Simulation(EventNotifier<Event> inEn, ResponderComm inRci, List<Event> q)
    {
        en = inEn;
        rci = inRci; //if clock desyncs move this to run()
        activeEvents = new HashMap<>();
        queue = q;
        //System.out.println("TEST"); for(Event e : queue){System.out.println(e.toString());} //DEBUG - Prints event queue
    }

    /************************************************************
     * outgoing message and validates it.
     * @throws InterruptedException
     ************************************************************/
    public void run() throws InterruptedException
    {

        boolean simIsActive = true;
        int seconds = 0; //timer init

        System.out.println("Starting Simulation...");

        while (simIsActive) {

            simIsActive = poll(simIsActive);
            sendStartMsg(seconds); //sends message to responders, adds to active event list
            simClockTick();

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
                    //prints report
                    System.out.println("Final Simulation Report");
                    for (Event e : queue) {
                        System.out.println(e.toString());
                    }
                }
                else
                {
                    try
                    {
                        receive(s); //adds responders arrival status
                    }
                    catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
                }
            }
        }
        return simIsActive;
    }

    /************************************************************
     * checks event queue for any events scheduled to start this second
     * creates message
     * @param seconds
     * @param activeEvents
     ************************************************************/
    private void sendStartMsg(int seconds) {
        for (Event nxt : queue) {
            if(nxt.getStartTime() == seconds) //if there any events scheduled to start this second
            {
                try {
                    activeEvents.put(nxt.getKey(), nxt); //add event to activeList
                    rci.send(en.notify(nxt, "start")); //sends events to responder - prints
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
        String temp = "";
        boolean needsRemoval = false;
        for (Event event : activeEvents.values())
        {
            if(event.getCleanupRemaining() > 0)//if not over
            {
                event.clockTick();
            }
            else
            {
                System.out.println(event.getEventType() + " at " + event.getLocation() + " is over");
                temp = event.getKey();
                needsRemoval = true;
            }
        }
        if(needsRemoval)
        {
            activeEvents.remove(temp); //removes event from active list
        }

    }

    /************************************************************
     * Validates then Formats message
     * Receives and sets arrival and leave status
    ************************************************************/
    public void receive(String s)
    {
        Matcher m = SEND_REGEX.matcher(s); //checks string against regex
        if(!m.matches()){
            throw new IllegalArgumentException("Invalid message format: '" + s + "'");
        }
        String emergency = m.group("emergency");
        String status = m.group("status");
        String location = m.group("location");

        if(activeEvents.containsKey(emergency+location)){
            Event match = activeEvents.get(emergency+location);
            //sets arrival + departure
            if(status.equals("+")){
                match.arrive();
            }
            else{
                match.leave();
            }
        }
        // else{
        //     //System.out.println("not found:" + emergency+location); //TODO: Remove
        //     System.out.println(s);
        // }

    }

}
