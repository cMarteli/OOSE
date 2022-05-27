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
    public static final int FIRE_LOW_TO_HIGH_TIME = 10; //low intensity fire - time(seconds) it takes for a low intensity fire to turn high intensity

    private Event event;

    @Override
    public void arrive() {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset() {
        //Ignore

    }

    @Override
    public void checkCasualty(boolean result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkDamage(boolean result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkContam(boolean result) {
        // TODO Auto-generated method stub

    }


}
