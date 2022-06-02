/**
 * EventState.java
 * Interface that allows event objects to change states
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public interface EventState
{
    /** Performs actions according to the specific event type */
    public void clockTick(boolean rescuers);

    /** LowFire and HighFire events only - Allows state change returns 1 for low->high, 2 for high->low  */
    public int intensityChange();

    /** Checks for new casualties */
    public double checkCasualty();

    /** Checks for new property Damage/Contamination */
    public double checkDamage();

    /** Returns specific state's type as String (fire|flood|chemical) */
    public String getEventType();

    /** Returns total cleanup time of the specific state */
    public int checkCleanupTotal();

}
