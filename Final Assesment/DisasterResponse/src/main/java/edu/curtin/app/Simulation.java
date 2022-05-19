/**
 * Simulation.java
 */
package edu.curtin.app;
import java.util.ArrayList;


public class Simulation
{

    ArrayList<Event> list;

    /************************************************************
    IMPORT: none
    EXPORT: void
    ASSERTION: Constructor
    ************************************************************/
    public Simulation()
    {
        list = new ArrayList<>();
    }

    public void addEvent(Event e)
    {
        list.add(e);
    }

    public void removeEvent(Event e)
    {
        list.remove(e);
    }

    public void printEventList()
    {
        for (Event e : list) //prints list contents
        {
            System.out.println(e.toString()); //prints array info
        }
    }

    //checks for duplicate events
    public boolean checkDupes(Event e)
    {
        boolean result = false;
        for (Event ev : list)
        {
            if(e.isSame(ev))
            {
                result = true;
            }
        }
        return result;
    }



}
