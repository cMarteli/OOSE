/**
 * EventNotifier.java
 * Observable/Subject Interface
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface EventNotifier<E>
{
    public void register(Observer o);

    public void unregister(Observer o);

    public String notify(E e, String msgType);

}
