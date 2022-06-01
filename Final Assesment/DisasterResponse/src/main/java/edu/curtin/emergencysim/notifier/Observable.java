/**
 * Observable.java
 * Observable/Subject Interface
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface Observable
{
    public void register(IObserver newObs);

    public void unregister(IObserver delObs);

    public void notifyObserver(String msg);
}