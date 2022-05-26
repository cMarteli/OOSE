/**
 * EventNotifier.java
 * Observable/Subject Interface
 */
package edu.curtin.emergencysim;

import java.util.List;

public interface EventNotifier<E>
{
    public List<E> getEventQueue(); //Generic type allows for the use of either string or event class depending on implementation

    public E receive(String s);

    public String notify(E e);

    public void addEvent(E e);

    public void addEvent(int time, String type, String loc);

    public E getActiveEvent(String type, String loc);

    public List<E> getActiveEvents();

    public void removeEvent(E e);

    public boolean checkDupes(String type, String loc);

}
