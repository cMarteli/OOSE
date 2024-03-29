/**
 * Observable.java
 * Observable/Subject Interface
 * Allows use of observer pattern by keeping track of
 * subscriber list
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface IObservable
{
    public void register(IObserver newObs);

    public void unregister(IObserver delObs);

    public void notifyObserver(String msg);
}