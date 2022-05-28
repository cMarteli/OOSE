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
    public static final int FIRE_HIGH_TO_LOW_TIME = 5; //high intensity fire - time(seconds) it takes for a high intensity fire to turn low intensity

    private Event event;

    public FireHigh(Event e) {
        event = e;
        event.setCleanupRemaining(FIRE_HIGH_TO_LOW_TIME);
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public boolean checkCasualty() {
        return roll(FIRE_HIGH_CASUALTY_PROB);
    }

    @Override
    public boolean checkDamage() {
        return roll(FIRE_HIGH_DAMAGE_PROB);
    }

    @Override
    public void clockTick(boolean rescuers) {

        if(rescuers) //rescuers are present
        {   //check fire has been fought enough
            if(event.getCleanupRemaining() <= 0)
            {
                event.intensityChange(); //change state to low
            }
            else
            {
                event.setCleanupRemaining(event.getCleanupRemaining() - 1); //cleans up by 1
            }
        }
        event.checkCasualty();
        event.checkDamage();

    }

    /** low fire returns 1, high fire returns 2 */
    @Override
    public int intensityChange() {
        return 2;
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
        return FIRE_HIGH_TO_LOW_TIME;
    }


}
