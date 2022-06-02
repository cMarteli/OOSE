/**
 * Event.java
 * Implements EventState and Observable
 * This class utilises the state pattern to keep track of its current state
 * and the observer pattern to notify the simulation or any other subscribers of changes
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.*;

import edu.curtin.emergencysim.EmergencySim;
import edu.curtin.emergencysim.notifier.*;

public class Event implements EventState, IObservable
{
    /** States */
    private EventState eventState, flood, fireLow, fireHigh, chem;
    /** Observers */
    private List<IObserver> subscribers;
    /**Fields */
    private int startTime, casualtyCount, dmgCount, cleanupRemaining;
    private String location;
    private boolean rescuersPresent;

    /** Logger from EmergencyResponse.java  */
    private final static Logger LOGR = Logger.getLogger(EmergencySim.class.getName());

    /**
     * Constructor
     * @param t
     * @param et
     * @param l
     */
    public Event(int t, String et, String l)
    {
        subscribers = new ArrayList<>();

        flood = new Flood(this);
        fireLow = new FireLow(this);
        fireHigh = new FireHigh(this);
        chem = new Chemical(this);

        setEventState(et); //sets state according to string
        startTime = t;
        location = l;

        //initalizes counts to 0
        casualtyCount = 0;
        dmgCount = 0;
        rescuersPresent = false; //rescuers are not present at start
    }

    /**Setters  */

    /**
     * sets type and initiates cleanup time - method is final as it's used during construction
     * cleanup time is set to total time needed for cleanup depending on event type
     * @param state (String)
     */
    public final void setEventState(String state)
    {
        if(state.equals("fire"))
        {
            eventState = fireLow;
        }
        else if(state.equals("flood"))
        {
            eventState = flood;
        }
        else if(state.equals("chemical"))
        {
            eventState = chem;
        }
    }

    public void setCleanupRemaining(int total)
    {
        cleanupRemaining = total;
    }

    /**
     * Rescuers status setters
     */
    public void arrive()
    {
        rescuersPresent = true;

        if(LOGR.isLoggable(Level.INFO)){ LOGR.info("arrival:["+eventState.toString()+"]"); } //LOGGER.INFO: arrival
    }

    public void leave() {
        if(!eventState.getEventType().equals("flood")) //resets cleanup time except if flood
        {
            cleanupRemaining = checkCleanupTotal();
        }
        rescuersPresent = false;
        if(LOGR.isLoggable(Level.INFO)){ LOGR.info("left:["+eventState.toString()+"]"); } //LOGGER.INFO: leaving
    }

    /**Getters */
    public String getLocation()
    {
        return location;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getCleanupRemaining(){
        return cleanupRemaining;
    }

    public boolean rescuerStatus(){
        return rescuersPresent;
    }

    public int getCasualtyCount(){
        return casualtyCount;
    }

    public int getDmgCount(){
        return dmgCount;
    }

    /**
     * Compares two events given location and type
     * @param inType (String)
     * @param inLoc (String)
     * @return boolean
     */
    public boolean compare(String inType, String inLoc)
    {
        if(getEventType().equals(inType) &&
            location.toUpperCase().equals(inLoc.toUpperCase()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Clock tick. Changes depending on rescuer status */
    @Override
    public void clockTick(boolean rescuers) {
        eventState.clockTick(rescuersPresent);
    }

    /** Overloaded method gets rescuer status automatically */
    public void clockTick() {
        clockTick(rescuersPresent);
    }

    /** Toggles fire intensity, low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
        if(eventState.intensityChange() == 1)
        {
            notifyObserver("fire " + "high " + location);
            eventState = fireHigh;
        }
        else if(eventState.intensityChange() == 2)
        {
            notifyObserver("fire " + "low " + location);
            eventState = fireLow;
        }
        setCleanupRemaining(eventState.checkCleanupTotal());
        return 0;
    }

    /** Rolls and adds to casualty if true. Signals change to observers */
    @Override
    public double checkCasualty() {
        double casualtyProb = eventState.checkCasualty();
        if(roll(casualtyProb))
        {
            casualtyCount++;
            notifyObserver(eventState.getEventType() + " casualty " + casualtyCount + " " + location);

            if(LOGR.isLoggable(Level.INFO)){ LOGR.info("Casualty reported:["+eventState.toString()+"]"); } //LOGGER.INFO: Casualty
        }
        return casualtyProb;

    }

    /************************************************************
     * Generates random double to 2decimals given probability if
     * roll 'passes' if result is LOWER than probability
     * then pass it to clockTick()
     * @param prob
     * @return boolean
     ************************************************************/
    public boolean roll(double prob)
    {
        Random rand = new Random();
        double r = Math.floor(rand.nextDouble()*100) / 100;

        if(LOGR.isLoggable(Level.INFO)){ LOGR.info("(" + r + "/" + prob + ")"); } //LOGGER.INFO: probability
        return (r < prob);
    }

    /** Rolls and adds to damage if true. Signals change to observers */
    @Override
    public double checkDamage() {
        double dmgProb = eventState.checkDamage();
        if(roll(dmgProb))
        {
            String label = " damage ";
            if(eventState.getEventType().equals("chemical")){
                label = " contam ";
            }
            dmgCount++;
            notifyObserver(eventState.getEventType() + label + dmgCount + " " + location);

            if(LOGR.isLoggable(Level.INFO)){ LOGR.info("Damage reported:["+eventState.toString()+"]"); } //LOGGER.INFO: Damage
        }
        return dmgProb;
    }

    @Override
    public String getEventType() {
        return eventState.getEventType();
    }

    @Override
    public String toString()
    {
        return getEventType() + " at " + location + "\nCasualties: " + casualtyCount +
        eventState.toString();
    }


    @Override
    public int checkCleanupTotal() {
        return eventState.checkCleanupTotal();
    }

    /**
     * Generates unique event key.
     * Key=Type+Location
     * @return (String)
     */
    public String getKey()
    {
        return getEventType()+location;
    }

    /** Observer pattern methods */
    @Override
    public void register(IObserver newObs) { //adds observer to list
        subscribers.add(newObs);

        if(LOGR.isLoggable(Level.FINE)){ LOGR.fine("Observer " + subscribers.indexOf(newObs) + " added"); } //LOGGER.FINE: Sub
    }

    @Override
    public void unregister(IObserver delObs) { //removes observer from list
        int obsIndx = subscribers.indexOf(delObs);
        subscribers.remove(obsIndx);

        if(LOGR.isLoggable(Level.FINE)){ LOGR.fine("Observer " + (obsIndx+1) + " deleted"); } //LOGGER.FINE: Unsub
    }

    @Override
    public void notifyObserver(String msg) //notifies all observers
    {
        if(!msg.isEmpty()){
            for (IObserver observer : subscribers) {
                observer.update(msg);
            }
        }
    }


}
