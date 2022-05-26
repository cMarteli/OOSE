/**
 * EventNotifier.java
 * Observable/Subject Interface
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

import java.util.List;

public interface EventNotifier<E>
{
    /**
     * Generic type allows for the use of either string or event class depending on implementation
     * @return
     */
    public List<E> getEventQueue();

    public void receive(String s);

    public String notify(E e);

    public void addEvent(E e);

    public void addEvent(int time, String type, String loc);

    public void removeEvent(E e);

    public boolean checkDupes(String type, String loc);

}
