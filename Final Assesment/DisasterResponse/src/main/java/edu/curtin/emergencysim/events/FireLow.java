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
        idleTime = 0;
    }

    @Override
    public void clockTick(boolean rescuers) {
        if(rescuers) //rescuers are present
        {
            event.setCleanupRemaining(event.getCleanupRemaining() - 1); //cleans up by 1
        }
        else
        {   //check fire has been idle long enough
            if(idleTime >= FIRE_LOW_TO_HIGH_TIME)
            {
                event.intensityChange(); //change state to high
            }
            idleTime++;
        }

        event.checkCasualty();
        event.checkDamage();
    }

    /** low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
        idleTime = 0;
        return 1;
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public boolean checkCasualty() {
        return roll(FIRE_LOW_CASUALTY_PROB);
    }

    @Override
    public boolean checkDamage() {
        return roll(FIRE_LOW_DAMAGE_PROB);

    }

    @Override
    public String getEventType() {
        return "FIRE";
    }

    @Override
    public boolean checkContam() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCleanupTotal() {
        return FIRE_LOW_CLEANUP_TIME;
    }


}
