/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

import edu.curtin.emergencysim.notifier.IObserver;

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

    public void register(IObserver newObs);

    public void unregister(IObserver delObs);

    public void notifyObserver(String msg);

}
