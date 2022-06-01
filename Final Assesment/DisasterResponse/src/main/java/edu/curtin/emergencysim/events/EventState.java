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
    public double checkCasualty();

    /**checks for new property damage/ Contamination */
    public double checkDamage();

    public String getEventType();

    /** */
    public int checkCleanupTotal();

}
