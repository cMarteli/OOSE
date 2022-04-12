/**
 * Key.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import java.awt.Point;

public abstract class Wall implements Tile
{
    private Point location;

    public Wall(Point l)
    {
        location = l;
    }

    public String description()
    {
        return "Wall object";
    }

    public Point getLocation()
    {
        return location;
    }

}
