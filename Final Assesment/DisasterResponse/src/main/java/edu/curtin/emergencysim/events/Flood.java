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
    public void clockTick(boolean rescuers)
    {
        event.setCleanupRemaining(event.getCleanupRemaining() - 1); //always reduces by 1 no matter what
        event.checkDamage();
        if(!rescuers) //casualties will only happen if rescuers are absent
        {
            event.checkCasualty();
        }
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public double checkCasualty() {
        return FLOOD_CASUALTY_PROB;
    }

    @Override
    public double checkDamage() {
        return FLOOD_DAMAGE_PROB;
    }

    @Override
    public String getEventType() {
        return "flood";
    }

    @Override
    public int intensityChange() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCleanupTotal() {
        return FLOOD_END_TIME;
    }

    @Override
    public String toString()
    {
        return getEventType() + " at " + event.getLocation() + "\nCasualties: " + event.getCasualtyCount() +
        " - Damage: " + event.getDmgCount();
    }

}
