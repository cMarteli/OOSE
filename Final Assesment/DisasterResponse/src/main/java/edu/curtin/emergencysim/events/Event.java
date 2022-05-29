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

    private int startTime, casualtyCount, dmgCount, contamination, cleanupRemaining;
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
        cleanupRemaining = getCleanupTotal(); //sets total cleanup
    }

    /**
     * sets type and initiates cleanup time - method is final as it's used during construction
     * cleanup time is set to total time needed for cleanup depending on event type
     * @param state (String)
     */
    public final void setEventState(String state)
    {
        state = state.toUpperCase();
        if(state.equals("FIRE"))
        {
            eventState = fireLow;
        }
        else if(state.equals("FLOOD"))
        {
            eventState = flood;
        }
        else if(state.equals("CHEMICAL"))
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

    public int contamStatus(){
        return contamination;
    }

    /**
     * Overloaded method Compares with just location and type
     * @param inType (String)
     * @param inLoc (String)
     * @return
     */
    public boolean compare(String inType, String inLoc)
    {
        String type = eventState.getEventType();
        if(type.equals(inType.toUpperCase()) &&
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
        rescuersPresent = true;
    }

    public void leave() {
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
        //TODO: Pass lambda function here
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
            eventState = fireHigh;
        }
        else if(eventState.intensityChange() == 2)
        {
            eventState = fireLow;
        }
        return 0;
    }

    /**Resets cleanuptime back to default */
    private void resetCleanp() {
        setCleanupRemaining(eventState.getCleanupTotal());
    }

    @Override
    public boolean checkCasualty() {
        boolean result = eventState.checkCasualty();
        if(result)
        {
            casualtyCount++;
            //System.out.println("Casualty reported."); //TODO: PUT LOGGER HERE
        }
        return result;

    }



    @Override
    public boolean checkDamage() {
        boolean result = eventState.checkDamage();
        if(result)
        {
            dmgCount++;
            //System.out.println("Damage reported."); //TODO: PUT LOGGER HERE
        }
        return result;

    }

    @Override
    public boolean checkContam() {
        boolean result = eventState.checkContam();
        if(result)
        {
            contamination++;
            //System.out.println("Contamination reported."); //TODO: PUT LOGGER HERE
        }
        return result;
    }



    @Override
    public String getEventType() {
        return eventState.getEventType();
    }

    @Override
    public String toString()
    {
        return getEventType().toLowerCase() + " at" + location;
    }


    @Override
    public final int getCleanupTotal() {
        return eventState.getCleanupTotal();
    }



}
