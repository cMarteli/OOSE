/**
 * Key.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import java.awt.Point;

public interface Tile
{
     //Every key opens a door
    public String description();

    public Point getLocation();

}
