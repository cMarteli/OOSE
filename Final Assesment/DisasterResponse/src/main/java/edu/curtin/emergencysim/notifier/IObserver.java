/**
 * EventNotifier.java
 * Observer Interface
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface IObserver
{
    /**
     * Generic type allows for the use of either string or event class depending on implementation
     * @return
     */
    public void update(String msg);

}