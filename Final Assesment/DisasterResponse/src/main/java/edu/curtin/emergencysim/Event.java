/**
 * Event.java
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;

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

    /************************************************************
    Constructor
    ************************************************************/
    public Event(int t, Emergency e, String l) {
        time = t;
        emergencyType = e;
        location = l;

        setCasualtyCount(0); //initalizes counts to 0
        setDmgCount(0);
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
