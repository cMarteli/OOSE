/**
 * EventNotifier.java
 * Observer Interface
 * Allows observer pattern implementation
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface IObserver
{
    /** Subject may call this to notify Observer of a change */
    public void update(String msg);
}