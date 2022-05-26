/**
 * EventNotifier.java
 * Observable/Subject
 * Implentation which uses Event class
 */
package edu.curtin.emergencysim;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class EventNotifierImpl implements EventNotifier<Event>
{

    private List<Event> eventQueue; //list of references to events in file

    private List<Event> activeEvents; //list of references to current events

    // A regular expression for validating and extracting parts of outgoing ('send') messages.
    private static final Pattern SEND_REGEX = Pattern.compile(
    "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");

    /************************************************************
    Constructor
    ************************************************************/
    public EventNotifierImpl()
    {
        eventQueue = new ArrayList<>();
        activeEvents = new ArrayList<>();
    }

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
    public Event getNext()
    {
        Event next = eventQueue.get(0); //sets index 0 as next
        int temp = next.getTime(); //gets it's time
        for (Event em : eventQueue)
        {
            if(em.getTime() < temp) //if lower arrival time
            {
                next = em; //make it next
                temp = em.getTime();
            }
        }

        return next;
    }

    @Override
    public List<Event> getEventQueue()
    {
        return eventQueue;
    }

    @Override
    public List<Event> getActiveList()
    {
        return activeEvents;
    }

    /************************************************************
    IMPORT: e (Event)
    EXPORT: result (boolean)
    Checks for duplicate events. Used by fileIO.java
    ************************************************************/
    @Override
    public boolean checkDupes(String type, String loc)
    {
        boolean result = false;
        for (Event ev : eventQueue)
        {
            if(ev.isSame(type, loc))
            {
                result = true;
            }
        }
        return result;
    }


    //Validates then Formats message
    @Override
    public void receive(String s) throws IllegalArgumentException
    {
        Matcher m = SEND_REGEX.matcher(s); //checks string against regex
        if(!m.matches())
        {
            throw new IllegalArgumentException("Invalid message format: '" + s + "'");
        }
        String emergency = m.group("emergency");
        String status = m.group("status");
        String location = m.group("location");

        //displays message - sets rescuer status
        if(status.equals("+"))
        {
            System.out.println(emergency + " team arrived in " + location);
            getActEvent(emergency, location).setRescuersPresent(true); //crashes TODO
        }
        else
        {
            System.out.println(emergency + " team departed from " + location);
            getActEvent(emergency, location).setRescuersPresent(false);
        }
    }


    /************************************************************
    IMPORT: nxt (Event)
    EXPORT: outStr (String)
    Creates outgoing message and validates it.
    TODO: Need to send randomly generated casualties, dmg, etc
    as well as fire intensity increase events
    ************************************************************/
    @Override
    public String notify(Event e) throws IllegalArgumentException
    {
        String outStr;

        switch (e.getEmergencyType())
        {
            case FIRE:
                outStr = "fire low"+ e.getLocation(); //fire always starts at low intensity

                break;

            case FLOOD:
                outStr = "flood start " + e.getLocation();

                break;

            case CHEMICAL:
                outStr = "chemical start " + e.getLocation();

                break;

            default:
                throw new IllegalArgumentException("Invalid Emergency type: '" + e.getEmergencyType() + "'");
        }
        activeEvents.add(e); //add event to active list
        return outStr;
    }

    @Override
    public Event getActEvent(String type, String loc)
    {
        type = type.toUpperCase();
        //searches through active event list
        for (Event e : getActiveList())
        {
            if(e.isSame(type, loc))
            {
                return e; //found
            }
        }
        //if not found throw error
        throw new IllegalArgumentException("Couldn't find event of type '" + type + "' at " + loc);
    }


}