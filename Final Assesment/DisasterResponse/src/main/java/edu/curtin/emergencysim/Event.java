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
    private Emergency emergencyType;
    private int cleanupTime;

    /************************************************************
    Constructor
    ************************************************************/
    public Event(int t, Emergency e, String l) {
        time = t;
        emergencyType = e;
        location = l;

        setCasualtyCount(0); //initalizes counts to 0
        setDmgCount(0);
        resetCleanupTime(e);
    }

    //returns 0 if cleanuptime is 0 or less
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


    //initiates cleanup time
    public void resetCleanupTime(Emergency e) {
        if(e == Emergency.FIRE)
        {
            cleanupTime = FIRE_LOW_CLEANUP_TIME;

        }
        else if(e == Emergency.FLOOD) //Flood does not 'cleanup' it has an end time
        {
            cleanupTime = FLOOD_END_TIME;

        }
        else if(e == Emergency.CHEMICAL)
        {
            cleanupTime = CHEM_CLEANUP_TIME;
        }
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
        if(emergencyType == e.getEmergencyType() &&
            location.toLowerCase().equals(e.getLocation().toLowerCase()))
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
        return emergencyType;
    }

    @Override
    public String toString() {
        return "Event [Type:" + emergencyType + ", Location:" + location + ", Start Time:" + time + "]";
    }






}
