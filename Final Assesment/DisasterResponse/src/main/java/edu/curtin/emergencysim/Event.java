/**
 * Event.java
 *
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

    private int time, casualtyCount, dmgCount;
    private String location;
    private Emergency eType;
    private int cleanupTime;
    private boolean rescuersPresent;

    /************************************************************
    Constructor
    ************************************************************/
    public Event(int t, String et, String l) {
        time = t;
        //cleanup time is set to total time needed for cleanup depending on event type
        setType(et); //sets enum according to string
        location = l;

        //initalizes counts to 0
        casualtyCount = 0;
        dmgCount = 0;
    }

    public boolean areRescuersPresent() {
        return rescuersPresent;
    }

    public void setRescuersPresent(boolean rescuersPresent) {
        this.rescuersPresent = rescuersPresent;
    }

    //returns true if cleanuptime is 0 or less
    public boolean isOver()
    {
        return cleanupTime <= 0;
    }

    public int getCleanupTime() {
        return cleanupTime;
    }

    public void setCleanupTime(int cleanupTime) {
        this.cleanupTime = cleanupTime;
    }

    //ticks down cleanuptime one second
    public void cleanupTick()
    {
        cleanupTime--;
        //System.out.println(eType+"@"+location+": clnpTim=" + cleanupTime);//TODO: DEBUG
    }


    //sets type and initiates cleanup time - method is final as it's used during construction
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

    public int getDmgCount() {
        return dmgCount;
    }

    public void setDmgCount(int dc) {
        dmgCount = dc;
    }

    public int getCasualtyCount() {
        return casualtyCount;
    }

    public void setCasualtyCount(int cc) {
        casualtyCount = cc;
    }



    //Compares if event is the same as another
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

    //Overloaded method Compares with just location and type
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

    public int getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
    public Emergency getEmergencyType() {
        return eType;
    }

    //utility to make a key for hashmap
    public String getKey()
    {
        return eType.toString().toUpperCase()+location;
    }

    @Override
    public String toString() {
        return "Event [Type:" + eType + ", Location:" + location + ", Start Time:" + time + "]";
    }






}
