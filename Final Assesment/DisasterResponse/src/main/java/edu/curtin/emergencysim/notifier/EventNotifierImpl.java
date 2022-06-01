/**
 * EventNotifier.java
 * Observable/Subject
 * Implentation which uses Event class
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.notifier;
import java.util.ArrayList;
import java.util.List;

import edu.curtin.emergencysim.events.Event;



public class EventNotifierImpl implements EventNotifier<Event>
{

    private List<Event> subscribers; //list of references to events in file

    /************************************************************
    Constructor
    ************************************************************/
    public EventNotifierImpl()
    {
        subscribers = new ArrayList<>();
    }

    /************************************************************
    * Creates outgoing message and validates it.
    * TODO: Need to send randomly generated casualties, dmg, etc + fire intensity increase events
    * @param e (Event)
    * @export outStr (String)
    ************************************************************/
    @Override
    public String notify(Event e, String msgType)
    {
        String outStr;

        switch (msgType)
        {
            case "start": //TODO: needs to check for high fire
                if(e.getEventType().equals("fire")){
                    outStr = e.getEventType() + " low start " + e.getLocation(); //fire always starts at low intensity
                }else{
                    outStr = e.getEventType() + " start " + e.getLocation();
                }
                break;

            case "damage":
                outStr = e.getEventType() + " damage " + e.getDmgCount() + " " + e.getLocation();
                break;

            case "casualty":
                outStr = e.getEventType() + " casualty " + e.getCasualtyCount() + " " + e.getLocation();
                break;

            case "end":
                outStr = e.getEventType() + " end " + e.getLocation();
                break;

            default: //should never happen
                throw new IllegalArgumentException("Invalid Emergency type: '" + e.getEventType() + "'");
        }
        return outStr;
    }

    @Override
    public void register(Observer o) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregister(Observer o) {
        // TODO Auto-generated method stub

    }


}