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
        event.checkContam();
        event.checkCasualty();
    }

    @Override
    public boolean roll(double prob) {
        return event.roll(prob);
    }

    @Override
    public boolean checkCasualty() {
        return roll(CHEM_CASUALTY_PROB);
    }

    @Override
    public boolean checkDamage() {
        // TODO Auto-generated method stub
        return false;
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
    public boolean checkContam() {
        return roll(CHEM_CONTAM_PROB);
    }

    @Override
    public int getCleanupTotal() {
        return CHEM_CLEANUP_TIME;
    }

}
