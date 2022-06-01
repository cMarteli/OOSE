/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.curtin.emergencysim.notifier.IObserver;

public class Event implements EventState
{

    /** States */
    private EventState eventState;
    private EventState flood;
    private EventState fireLow;
    private EventState fireHigh;
    private EventState chem;

    private int startTime, casualtyCount, dmgCount, cleanupRemaining;
    private String location;
    private boolean rescuersPresent;

    private List<IObserver> subscribers; //list of all subscribed to this event

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
     * Overloaded method Compares with just location and type
     * @param inType (String)
     * @param inLoc (String)
     * @return
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


    public void arrive() {
        notifyObserver(getEventType() + " start " + location); //TODO: print low or high for fire
        rescuersPresent = true;
    }

    public void leave() {
        notifyObserver(getEventType() + " end " + location);
        if(!eventState.getEventType().equals("flood")) //resets cleanup time except if flood
        {
            cleanupRemaining = checkCleanupTotal();
        }
        rescuersPresent = false;
    }


    //clock tick changes depending on rescuer status
    @Override
    public void clockTick(boolean rescuers) {
        eventState.clockTick(rescuersPresent);
        //TODO: Pass lambda function here
    }

    //overloaded method to be called by simulation
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

    @Override
    public double checkCasualty() {
        double casualtyProb = eventState.checkCasualty();
        if(roll(casualtyProb))
        {
            casualtyCount++;
            notifyObserver(eventState.getEventType() + " casualty " + casualtyCount + " " + location);
            //System.out.println("Casualty reported."); //TODO: PUT LOGGER HERE
        }
        return casualtyProb;

    }

    /************************************************************
     * Generates random double to 2decimals given probability if
     * roll 'passes' if result is LOWER than probability
     * @param prob
     * @return boolean
     ************************************************************/
    public boolean roll(double prob)
    {
        Random rand = new Random();
        double r = Math.floor(rand.nextDouble()*100) / 100;
        //System.out.println("(" + r + "/" + prob + ")" ); //TODO: Put logger here
        return (r < prob);
    }



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
            //System.out.println("Damage reported."); //TODO: PUT LOGGER HERE
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

    //Type+Location
    public String getKey()
    {
        return getEventType()+location;
    }

    @Override
    public void register(IObserver newObs) {
        subscribers.add(newObs);

    }

    @Override
    public void unregister(IObserver delObs) {
        // Get index
        int obsIndx = subscribers.indexOf(delObs);
        //prints msg
        System.out.println("Observer " + (obsIndx+1) + " deleted");
        //removes from list
        subscribers.remove(obsIndx);
    }

    @Override
    public void notifyObserver(String msg)
    {
        if(!msg.isEmpty()){
            for (IObserver observer : subscribers) {
                observer.update(msg);
            }
        }
    }


}
