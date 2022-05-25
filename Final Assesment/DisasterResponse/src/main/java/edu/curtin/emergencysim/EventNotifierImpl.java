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

    private List<Event> events; //list of references to what's being observed

    // A regular expression for validating and extracting parts of outgoing ('send') messages.
    private static final Pattern SEND_REGEX = Pattern.compile(
    "(?<emergency>fire|flood|chemical) (?<status>[+-]) (?<location>.+)");

    /************************************************************
    Constructor
    ************************************************************/
    public EventNotifierImpl()
    {
        events = new ArrayList<>();
    }

    @Override
    public void addEvent(Event e)
    {
        events.add(e);
    }

    @Override
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

    @Override
    public List<Event> getEvents() //TODO possible place for generics <Event> <E>
    {
        return events;
    }

    /************************************************************
    IMPORT: e (Event)
    EXPORT: result (boolean)
    Checks for duplicate events. Used by fileIO.java
    ************************************************************/
    @Override
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

        //TODO: get active event and match it with this one
        //TODO: subtract one cleanup time every second


        //displays message
        if(status.equals("+"))
        {
            System.out.println(emergency + " team arrived in " + location);
        }
        else
        {
            System.out.println(emergency + " team departed from " + location);
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
        return outStr;
    }
}