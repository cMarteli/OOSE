/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public class Flood implements EventState
{
    //Constants for FLOOD
    public static final int FLOOD_END_TIME = 15; //time(seconds) for a flood to dissipate on it's own
    public static final double FLOOD_DAMAGE_PROB = 0.75; //probability of flood destroying a property
    public static final double FLOOD_CASUALTY_PROB = 0.75; //probability of hospitalisation. (If flood rescuers are present, hospitalisation = 0.)

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
