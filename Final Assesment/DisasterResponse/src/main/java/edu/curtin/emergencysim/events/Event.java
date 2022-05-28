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

    private int startTime, casualtyCount, dmgCount, cleanupTime;
    private String location;
    private boolean contamination;
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

    /**Getters */
    public int getStartTime(){
        return startTime;
    }

    public int getCleanupTime(){
        return cleanupTime;
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

    public boolean contamStatus(){
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
        System.out.println("Type: " + type + "InType: " + inType);
        System.out.println("loc: " + location + "inLoc: " + inLoc);
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
        cleanupTime = 0;
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
    }

    //overloaded method to be called by simulation
    public void clockTick() {
        eventState.clockTick(rescuersPresent);
    }


    /** Toggles fire intensity, low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
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
        return getEventType() + "at" + location;
    }



    @Override
    public boolean checkContam() {
        // TODO Auto-generated method stub
        return false;
    }

}
