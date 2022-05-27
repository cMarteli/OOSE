/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
import static edu.curtin.emergencysim.Constants.*; //imports constants class

public class Event
{
    enum Emergency
    {
        FIRE,
        FLOOD,
        CHEMICAL
    }

    private int startTime;
    //, casualtyCount, dmgCount;
    private String location;
    private Emergency eType;
    private int cleanupTime;
    private boolean rescuersPresent;

    /**
     * Constructor
     * @param t
     * @param et
     * @param l
     */
    public Event(int t, String et, String l) {
        startTime = t;
        //cleanup time is set to total time needed for cleanup depending on event type
        setType(et); //sets enum according to string
        location = l;

        //initalizes counts to 0
        //casualtyCount = 0;
        //dmgCount = 0;

        //rescuers are not present at start
        rescuersPresent = false;
    }

    /**
     * sets type and initiates cleanup time - method is final as it's used during construction
     * @param et (String)
     */
    public final void setType(String et)
    {
        if(et.equals(Emergency.FIRE.toString()))
        {
            eType = Emergency.FIRE;
            cleanupTime = FIRE_LOW_CLEANUP_TIME;

        }
        else if(et.equals(Emergency.FLOOD.toString()))
        {
            eType = Emergency.FLOOD;
            cleanupTime = FLOOD_END_TIME;

        }
        else if(et.equals(Emergency.CHEMICAL.toString()))
        {
            eType = Emergency.CHEMICAL;
            cleanupTime = CHEM_CLEANUP_TIME;
        }
        // else //This should never happen
        // {
        //     assert false: "ERROR: No match found when contructing event of type: " + et;
        // }
    }

    /**
     * Compares if event is the same as another
     * @param e (Event)
     * @return
     */
    public boolean isSame(Event e)
    {
        if(eType == e.getEmergencyType() &&
            location.equals(e.getLocation()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Overloaded method Compares with just location and type
     * @param inType (String)
     * @param inLoc (String)
     * @return
     */
    public boolean isSame(String inType, String inLoc)
    {
        if(eType.toString().toUpperCase().equals(inType.toUpperCase()) &&
            location.toUpperCase().equals(inLoc.toUpperCase()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Getters  */
    public boolean rescuersPresent() {
        return rescuersPresent;
    }
    public int getStartTime() {
        return startTime;
    }
    public String getLocation() {
        return location;
    }
    public Emergency getEmergencyType() {
        return eType;
    }
    /**@return true if cleanuptime is 0 or less */
    public boolean isOver()
    {
        return cleanupTime <= 0;
    }
    /**@return key for hashmap  */
    public String getKey()
    {
        return eType.toString().toUpperCase()+location;
    }

    /** Setters  */
    public void setRescuersPresent(boolean status) {
        rescuersPresent = status;
    }
    /**ticks down cleanuptime one second */
    public void cleanup()
    {
        cleanupTime--;
        //System.out.println(eType+"@"+location+": clnpTim=" + cleanupTime);//TODO: DEBUG
    }
    @Override
    public String toString() {
        return "Event [Type:" + eType + ", Location:" + location + ", Start Time:" + startTime + "]";
    }


}
