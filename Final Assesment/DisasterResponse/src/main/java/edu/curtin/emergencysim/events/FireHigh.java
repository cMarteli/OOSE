/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public class FireHigh implements EventState
{
    //constants for FIRE - High (A high level fire must be reduced to low before it can be extinguished)
    public static final double FIRE_HIGH_CASUALTY_PROB = 0.45; //high intensity fire - probability of hospitalising someone
    public static final double FIRE_HIGH_DAMAGE_PROB = 0.75; //high intensity fire - probability of destroying a property
    public static final int FIRE_HIGH_TO_LOW_TIME = 30; //high intensity fire - time(seconds) it takes for a high intensity fire to turn low intensity

    private Event event;

    @Override
    public void arrive() {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset() {
        // Ignore

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
