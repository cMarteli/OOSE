/**
 * RedKey.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public class RedKey implements Key
{
    //constructor
    public RedKey()
    {
        System.out.println("Added RED KEY to Inventory");
    }
     //Every key opens a door
    public String description()
    {
        return "Opens RED DOOR";

    }

    // public Point getLocation();

}
