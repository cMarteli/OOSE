/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public class FireLow implements EventState
{
    //constants for FIRE - Low
    public static final int FIRE_LOW_CLEANUP_TIME = 5; //low intensity fire - time(seconds) to actively extinguish
    public static final double FIRE_LOW_CASUALTY_PROB = 0.15; //low intensity fire - probability (0–1) of hospitalising someone
    public static final double FIRE_LOW_DAMAGE_PROB = 0.45; //low intensity fire - probability of destroying a property
    public static final int FIRE_LOW_TO_HIGH_TIME = 5; //low intensity fire - time(seconds) it takes for a low intensity fire to turn high intensity

    private Event event;
    private int idleTime;

    public FireLow(Event e)
    {
        event = e;
        event.setCleanupRemaining(FIRE_LOW_CLEANUP_TIME);
        idleTime = 0; //initial idle counter
    }

    @Override
    public void clockTick(boolean rescuers) {
        if(rescuers) //rescuers are present
        {
            idleTime = 0; //every second responders are present idle counter is reset
            event.setCleanupRemaining(event.getCleanupRemaining() - 1); //cleans up by 1
        }
        else //check fire has been idle long enough
        {
            idleTime++;
            if(idleTime >= FIRE_LOW_TO_HIGH_TIME)
            {
                idleTime = 0;//reset counter before intensity change
                event.intensityChange(); //change state to high
            }
        }
        event.checkCasualty();
        event.checkDamage();
        //System.out.println("idletime=["+idleTime +"] "+event.getLocation()); //TODO: Remove
    }

    /** low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
        return 1;
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public double checkCasualty() {
        return FIRE_LOW_CASUALTY_PROB;
        //TODO: make these methods return ints
    }

    @Override
    public double checkDamage() {
        return FIRE_LOW_DAMAGE_PROB;

    }

    @Override
    public String getEventType() {
        return "fire";
    }

    @Override
    public int getCleanupTotal() {
        return FIRE_LOW_CLEANUP_TIME;
    }

    @Override
    public String toString()
    {
        return getEventType() + " at " + event.getLocation() + "\nCasualties: " + event.getCasualtyCount() +
        " - Damage: " + event.getDmgCount();
    }


}
