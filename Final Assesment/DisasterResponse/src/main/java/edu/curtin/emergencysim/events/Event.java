/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

import java.util.Random;

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

    /**
     * Constructor
     * @param t
     * @param et
     * @param l
     */
    public Event(int t, String et, String l)
    {
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
            // fireLow = new FireLow(this);
            // fireHigh = new FireHigh(this);
            eventState = fireLow;
        }
        else if(state.equals("flood"))
        {
            //flood = new Flood(this);
            eventState = flood;
        }
        else if(state.equals("chemical"))
        {
            //chem = new Chemical(this);
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
        System.out.println(getEventType() + " team arrived in " + location); //TODO DEBUG
        rescuersPresent = true;
    }

    public void leave() {
        System.out.println("Team departed active " + getEventType() + " at " + location); //TODO DEBUG
        if(!eventState.getEventType().equals("FLOOD")) //resets cleanup time except if flood
        {
            resetCleanp();
        }
        rescuersPresent = false;
    }

    /************************************************************
     * Generates random double to 2decimals given probability if
     * roll 'passes' if result is LOWER than probability
     * @param prob
     * @return boolean
     ************************************************************/
    @Override
    public boolean roll(double prob)
    {
        Random rand = new Random();
        double r = Math.floor(rand.nextDouble()*100) / 100;
        //System.out.println("(" + r + "/" + prob + ")" ); //TODO: Put logger here
        return (r < prob);
    }



    //clock tick changes depending on rescuer status
    @Override
    public void clockTick(boolean rescuers) {
        eventState.clockTick(rescuersPresent);
        //TODO: Pass roll lambda function here
    }

    //overloaded method to be called by simulation
    public void clockTick() {
        clockTick(rescuersPresent);
    }


    /** Toggles fire intensity, low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
        resetCleanp(); //resets cleanup remaining before state change
        if(eventState.intensityChange() == 1)
        {
            System.out.println("Fire in " + location + " changed to High"); //TODO: DEBUG
            eventState = fireHigh;
        }
        else if(eventState.intensityChange() == 2)
        {
            System.out.println("Fire in " + location + " changed to Low"); //TODO: DEBUG
            eventState = fireLow;
        }
        return 0;
    }

    /**Resets cleanuptime back to default */
    private void resetCleanp() {
        setCleanupRemaining(eventState.getCleanupTotal());
    }

    @Override
    public double checkCasualty() {
        if(roll(eventState.checkCasualty()))
        {
            casualtyCount++;
            //System.out.println("Casualty reported."); //TODO: PUT LOGGER HERE
        }
        return 0.0;

    }



    @Override
    public double checkDamage() {
        if(roll(eventState.checkDamage()))
        {
            dmgCount++;
            //System.out.println("Damage reported."); //TODO: PUT LOGGER HERE
        }
        return 0.0;

    }


    @Override
    public String getEventType() {
        return eventState.getEventType();
    }

    @Override
    public String toString()
    {
        return eventState.toString();
    }


    @Override
    public int getCleanupTotal() {
        return eventState.getCleanupTotal();
    }

    //Type+Location
    public String getKey()
    {
        return getEventType()+location;
    }



}
