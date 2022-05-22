/**
 * EventNotifier.java
 * Observable
 */
package edu.curtin.emergencysim;
import java.util.ArrayList;
import java.util.List;



public class EventNotifier
{

    private List<Event> events; //reference to observers

    /************************************************************
    Constructor
    ************************************************************/
    public EventNotifier()
    {
        events = new ArrayList<>();
    }

    public void addEvent(Event e)
    {
        events.add(e);
    }

    public void removeEvent(Event e)
    {
        events.remove(e);
    }

    //get next by arrival time TODO: Currently not used
    public Event getNext()
    {
        Event next = events.get(0); //sets index 0 as next
        int temp = next.getTime(); //gets it's time
        for (Event em : events)
        {
            if(em.getTime() < temp) //if lower arrival time
            {
                next = em; //make it next
                temp = em.getTime();
            }
        }

        return next;
    }

    public List<Event> getEvents() //TODO possible place for generics <Event> <E>
    {
        return events;
    }

    /************************************************************
    IMPORT: e (Event)
    EXPORT: result (boolean)
    Checks for duplicate events. Used by fileIO.java
    ************************************************************/
    public boolean checkDupes(Event e)
    {
        boolean result = false;
        for (Event ev : events)
        {
            if(e.isSame(ev))
            {
                result = true;
            }
        }
        return result;
    }



}
