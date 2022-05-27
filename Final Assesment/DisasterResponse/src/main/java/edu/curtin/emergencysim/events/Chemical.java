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
