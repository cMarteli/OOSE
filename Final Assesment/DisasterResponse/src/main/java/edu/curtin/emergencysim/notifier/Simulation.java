/**
 * Simulation.java
 * Implements IObserver
 * Contains main simulation loop,
 * Observes events and communicates with ResponderComm
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;

import edu.curtin.emergencysim.EmergencySim;
import edu.curtin.emergencysim.events.*;
import edu.curtin.emergencysim.responders.*;

public class Simulation implements IObserver
{
    private ResponderComm rci; //allows comms with responders
    private Map<String, Event> activeEvents; //map with only events still ongoing
    private List<Event> queue; //queue of all event from file

    /** Regex for validating responder messages */
    public static final Pattern SEND_REGEX = Pattern.compile(
        "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");
    /** Logger from EmergencyResponse.java  */
    private final static Logger LOGR = Logger.getLogger(EmergencySim.class.getName());

    /************************************************************
     * Constructor: creates new RNG and RCI
     * @param inRci
     * @param q
    ************************************************************/
    public Simulation(ResponderComm inRci, List<Event> q)
    {
        rci = inRci; //if clock desyncs move this to run()
        activeEvents = new HashMap<>();
        queue = q;
    }

    /************************************************************
     * Loop that contains simulation logic.
     * Will run until receives and "end" message or an exception is thrown
     * @throws InterruptedException
     ************************************************************/
    public void run() throws InterruptedException
    {
        //The simulation subcribes to all events in list
        for (Event event : queue) {
            event.register(this);
        }

        boolean simIsActive = true;
        int seconds = 0; //timer init

        System.out.println("Starting Simulation...");

        while (simIsActive) {

            simIsActive = poll(simIsActive); //polls responders; sets active status if end is received
            eventStart(seconds); //sends message to responders, adds to active event list
            simClockTick();

            Thread.sleep(1000); //sleeps for 1 second
            seconds++; //increment timer

            if(LOGR.isLoggable(Level.INFO)){ LOGR.info("[t="+seconds+"]"); } //LOGGER: time passed LVL=INFO
        }

        printReport();//prints final report
    }

    /************************************************************
     * Gets message list from responders, checks for end condition
     * then adds/removes responders
     * @param simIsActive
     * @return simIsActive (Boolean)
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
     * Checks event queue for any events scheduled to start this second
     * notifies responders
     * @param seconds
     ************************************************************/
    private void eventStart(int seconds) {
        for (Event nxt : queue) {
            if(nxt.getStartTime() == seconds) //if there any events scheduled to start this second
            {
                activeEvents.put(nxt.getKey(), nxt); //add event to activeList
                update(nxt.getEventType() + " start " + nxt.getLocation()); //sends to responders
            }
        }
    }

    /************************************************************
     * Iterates through active event map and ticks the clock down on each
     * also removes events which are over
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
     * @param s (String)
     * @throws IllegalArgumentException
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

    /** Prints final report including damage, contam and casualties */
    private void printReport() {
        System.out.println("Final Simulation Report");
        for (Event e : queue) {
            System.out.println(e.toString());
        }
        System.out.println("End of Simulation.");
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
