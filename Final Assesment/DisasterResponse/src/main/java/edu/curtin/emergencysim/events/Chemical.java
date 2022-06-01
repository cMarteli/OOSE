/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public class Chemical implements EventState
{
    //chemical
    public static final int CHEM_CLEANUP_TIME = 5; //time(seconds) to actively clean a chemical spill
    public static final double CHEM_CONTAM_PROB = 0.45; //probability of environmental contamination
    public static final double CHEM_CASUALTY_PROB = 0.2; //probability of hospitalising someone

    private Event event;

    public Chemical(Event event) {
        this.event = event;
        event.setCleanupRemaining(CHEM_CLEANUP_TIME);
    }

    //TODO: not complete, needs testing
    @Override
    public void clockTick(boolean rescuers) {
        if(rescuers)
        {
            event.setCleanupRemaining(event.getCleanupRemaining() - 1); //cleans up by 1
        }
        event.checkDamage();
        event.checkCasualty();
    }

    @Override
    public double checkCasualty() {
        return CHEM_CASUALTY_PROB;
    }

    @Override
    public double checkDamage() {
        return CHEM_CONTAM_PROB;
    }

    @Override
    public String getEventType() {
        return "chemical";
    }

    @Override
    public int intensityChange() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int checkCleanupTotal() {
        return CHEM_CLEANUP_TIME;
    }

    @Override
    public String toString()
    {
        return " Contamination: " + event.getDmgCount();
    }

}
