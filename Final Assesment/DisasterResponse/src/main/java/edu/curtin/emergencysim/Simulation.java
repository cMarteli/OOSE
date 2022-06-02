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
import edu.curtin.emergencysim.responders.*;
import edu.curtin.emergencysim.notifier.*;

public class Simulation implements IObserver
{
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
    public Simulation(ResponderComm inRci, List<Event> q)
    {
        rci = inRci; //if clock desyncs move this to run()
        activeEvents = new HashMap<>();
        queue = q;
    }

    /************************************************************
     * outgoing message and validates it.
     * @throws InterruptedException
     ************************************************************/
    public void run() throws InterruptedException
    {
        //subcribe to all events in list
        for (Event event : queue) {
            event.register(this);
        }

        boolean simIsActive = true;
        int seconds = 0; //timer init

        System.out.println("Starting Simulation...");

        while (simIsActive) {

            simIsActive = poll(simIsActive);
            sendStartMsg(seconds); //sends message to responders, adds to active event list
            simClockTick();

            Thread.sleep(1000); //sleeps for 1 second
            seconds++;

            //System.out.print("[t="+seconds+"]"); //DEBUG: prints seconds
            if(LOGR.isLoggable(Level.INFO)){ LOGR.info(seconds + "s"); } //LOGGER: time passed LVL=INFO
        }

        printReport();//prints final report
    }

    /**
     * Prints final report including damage, contam and casualties
     */
    private void printReport() {
        System.out.println("Final Simulation Report");
        for (Event e : queue) {
            System.out.println(e.toString());
        }
        System.out.println("End of Simulation.");
    }

    /************************************************************
     * gets message from responders and formats the prints; clocktick
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
                    try{
                        checkArrival(s); //adds responders arrival status
                    }
                    catch (IllegalArgumentException e) {//incorrect message type
                        if (LOGR.isLoggable(Level.SEVERE)){ LOGR.log(Level.SEVERE, "Illegal Argument: " + e.getMessage());}
                    }
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
                activeEvents.put(nxt.getKey(), nxt); //add event to activeList
                update(nxt.getEventType() + " start " + nxt.getLocation()); //sends to responders
            }
        }
    }

    /************************************************************
    @param active (Map<String, Event>)
    Iterates through active event map and ticks the clock down on each
    also removes events which are over
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
                update(event.getEventType() + " end " + event.getLocation());
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
    public void checkArrival(String s) throws IllegalArgumentException
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
    }

    /**
     * IObserver interface method
     * Informs responders
     * @param msg
     */
    @Override
    public void update(String msg) {
        System.out.println();
        rci.send(msg);
    }

}
