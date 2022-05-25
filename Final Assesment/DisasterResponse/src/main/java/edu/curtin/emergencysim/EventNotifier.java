/**
 * EventNotifier.java
 * Observable/Subject Interface
 */
package edu.curtin.emergencysim;

import java.util.List;

public interface EventNotifier<E>
{
    public List<E> getEvents(); //Generic type allows for the use of either string or event class depending on implementation

    public void receive(String s);

    public String notify(E e);

    public void addEvent(E e);

    public void removeEvent(E e);

    public boolean checkDupes(E e);

}
