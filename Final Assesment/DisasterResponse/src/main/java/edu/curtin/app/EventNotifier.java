/**
 * EventNotifier.java
 * * Observable
 */
package edu.curtin.app;
import java.util.ArrayList;


public class EventNotifier
{

    private ArrayList<Emergency> events; //reference to observers

    /************************************************************
    IMPORT: none
    EXPORT: void
    ASSERTION: Constructor
    ************************************************************/
    public EventNotifier()
    {
        events = new ArrayList<>();
    }

    public void addEvent(Emergency e)
    {
        events.add(e);
    }

    public void removeEvent(Emergency e)
    {
        events.remove(e);
    }

    public void printEventList()
    {
        for (Emergency e : events) //prints list contents
        {
            System.out.println(e.toString()); //prints array info
        }
    }

    //checks for duplicate events
    public boolean checkDupes(Emergency e)
    {
        boolean result = false;
        for (Emergency ev : events)
        {
            if(e.isSame(ev))
            {
                result = true;
            }
        }
        return result;
    }



}
