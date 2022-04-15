/**
 * Tile.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import java.awt.Point;
import java.util.ArrayList;

public interface Tile
{
    //Returns list of tile objects contained in tile
    public ArrayList<Tile> contains();

    //Returns coordinates of this tile
    public Point getLocation();

}
