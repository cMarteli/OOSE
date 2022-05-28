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

    public Flood(Event event) {
        this.event = event;
    }

    @Override
    public void clockTick(boolean rescuers) {
        if(!rescuers)
        {
            checkCasualty();
            checkDamage();
        }
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public boolean checkCasualty() {
        return roll(FLOOD_CASUALTY_PROB);
    }

    @Override
    public boolean checkDamage() {
        return roll(FLOOD_DAMAGE_PROB);
    }

    @Override
    public String getEventType() {
        return "FLOOD";
    }

    @Override
    public int intensityChange() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean checkContam() {
        // TODO Auto-generated method stub
        return false;
    }

}
