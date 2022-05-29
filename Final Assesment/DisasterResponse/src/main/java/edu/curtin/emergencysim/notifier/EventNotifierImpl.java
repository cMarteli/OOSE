/**
 * EventNotifier.java
 * Observable/Subject
 * Implentation which uses Event class
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.curtin.emergencysim.events.Event;



public class EventNotifierImpl implements EventNotifier<Event>
{

    private List<Event> eventQueue; //list of references to events in file

    // A regular expression for validating and extracting parts of outgoing ('send') messages.
    private static final Pattern SEND_REGEX = Pattern.compile(
    "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");

    /************************************************************
    Constructor
    ************************************************************/
    public EventNotifierImpl()
    {
        eventQueue = new ArrayList<>();
    }

    /** Setters */
    @Override
    public void addEvent(Event e)
    {
        eventQueue.add(e);
    }

    @Override
    public void addEvent(int time, String type, String loc)
    {
        Event e = new Event(time, type, loc);
        eventQueue.add(e);
    }

    @Override
    public void removeEvent(Event e)
    {
        eventQueue.remove(e);
    }

    //get next by arrival time TODO: Currently not used
    // public Event getNext()
    // {
    //     Event next = eventQueue.get(0); //sets index 0 as next
    //     int temp = next.getStartTime(); //gets it's time
    //     for (Event em : eventQueue)
    //     {
    //         if(em.getStartTime() < temp) //if lower arrival time
    //         {
    //             next = em; //make it next
    //             temp = em.getStartTime();
    //         }
    //     }

    //     return next;
    // }

    /** Getters */
    @Override
    public List<Event> getEventQueue()
    {
        return eventQueue;
    }


    /************************************************************
    * Checks for duplicate events. Used by fileIO.java
    * @param type (String)
    * @param loc (String)
    ************************************************************/
    @Override
    public boolean checkDupes(String type, String loc)
    {
        boolean result = false;
        for (Event ev : eventQueue)
        {
            if(ev.compare(type, loc))
            {
                result = true;
            }
        }
        return result;
    }


    /************************************************************
     * Validates Message and returns if valid. Throws error if not
     * TODO: useless
    ************************************************************/
    @Override
    public String validateMsg(String s) throws IllegalArgumentException
    {
        //String outString = "";
        Matcher m = SEND_REGEX.matcher(s); //checks string against regex
        if(!m.matches())
        {
            throw new IllegalArgumentException("Invalid message format: '" + s + "'");
        }

        return s;
    }


    /************************************************************
    * Creates outgoing message and validates it.
    * TODO: Need to send randomly generated casualties, dmg, etc + fire intensity increase events
    * @param e (Event)
    * @export outStr (String)
    ************************************************************/
    @Override
    public String notify(Event e) throws IllegalArgumentException
    {
        String outStr;

        switch (e.getEventType())
        {
            case "fire":
                outStr = "fire low "+ e.getLocation(); //fire always starts at low intensity

                break;

            case "flood":
                outStr = "flood start " + e.getLocation();

                break;

            case "chemical":
                outStr = "chemical start " + e.getLocation();

                break;

            default: //should never happen
                throw new IllegalArgumentException("Invalid Emergency type: '" + e.getEventType() + "'");
        }
        return outStr;
    }


}