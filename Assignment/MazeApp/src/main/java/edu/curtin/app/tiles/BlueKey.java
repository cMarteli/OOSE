/**
 * RedKey.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

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
    @Override
    public Boolean use(String colour) {
        // TODO Auto-generated method stub
        return null;
    }

    // public Point getLocation();

}
