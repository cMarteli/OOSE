/**
 * RedKey.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

public class BlueKey implements Key
{
    //constructor
    public BlueKey()
    {
        System.out.println("Added BLUE KEY to inventory");
    }
     //Every key opens a door
    public String description()
    {
        return "Opens BLUE DOOR";

    }

    // public Point getLocation();

}
