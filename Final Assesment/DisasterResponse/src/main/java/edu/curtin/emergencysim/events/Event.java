/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public class Event implements EventState
{

    /** States */
    /* package */ EventState eventState;
    /* package */ EventState flood;
    /* package */ EventState fireLow;
    /* package */ EventState fireHigh;
    /* package */ EventState chem;

    /* package */ int startTime, casualtyCount, dmgCount, cleanupTime;
    /* package */ String location;
    /* package */ boolean rescuersPresent;

    /**
     * Constructor
     * @param t
     * @param et
     * @param l
     */
    public Event(int t, String et, String l)
    {
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
    public int getStartTime() {
        return startTime;
    }

    /**
     * Overloaded method Compares with just location and type
     * @param inType (String)
     * @param inLoc (String)
     * @return
     */
    public boolean compare(String inType, String inLoc)
    {
        if(eventState.toString().toUpperCase().equals(inType.toUpperCase()) &&
            location.toUpperCase().equals(inLoc.toUpperCase()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void arrive() {
        eventState.arrive();
    }

    @Override
    public void reset() {
        cleanupTime = 0;
    }

    @Override
    public void checkCasualty(boolean result) {
        eventState.checkCasualty(result);
    }

    @Override
    public void checkDamage(boolean result) {
        eventState.checkDamage(result);
    }

    @Override
    public void checkContam(boolean result) {
        eventState.checkContam(result);
    }

}
