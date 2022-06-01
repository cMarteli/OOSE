/**
 * EventNotifier.java
 * Observable/Subject Interface
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;

public interface Observer
{
    /**
     * Generic type allows for the use of either string or event class depending on implementation
     * @return
     */
    public void update(int dmg, int cas, int status);


}

/**
 * https://www.youtube.com/watch?v=wiQdrH2YpT4&t=363s
 * https://www.newthinktank.com/2012/08/observer-design-pattern-tutorial/
 */
