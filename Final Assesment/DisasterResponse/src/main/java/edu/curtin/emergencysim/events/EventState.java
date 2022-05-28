/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public interface EventState
{
    public void clockTick(boolean rescuers);

    public int intensityChange();

    /**checks for new casualties */
    public boolean checkCasualty();

    /**checks for new property damage */
    public boolean checkDamage();

    /**checks for chemical contamination */
    public boolean checkContam();

    /**Utility - Checks probability */
    public boolean roll(double prob);

    public String getEventType();

    /** */
    public int getCleanupTotal();

}
