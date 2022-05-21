/**
 * Event.java
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;


public class Emergency
{
    enum Disaster
    {
        FIRE,
        FLOOD,
        CHEMICAL
    }
    private int time;
    private String location;
    private Disaster dis;

    public Emergency(int time, Disaster dis, String location) {
        this.time = time;
        this.location = location;
        this.dis = dis;
    }

    //Compares if event is the same as another
    public boolean isSame(Emergency e)
    {
        if(dis == e.getDis() &&
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
    public void setTime(int time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Disaster getDis() {
        return dis;
    }
    public void setDis(Disaster dis) {
        this.dis = dis;
    }

    @Override
    public String toString() {
        return "Event [dis=" + dis + ", location=" + location + ", time=" + time + "]";
    }






}
