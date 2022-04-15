/**
 * EWwall.java
 * East -> West wall
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import java.awt.Point;
import java.security.Principal;
import java.util.ArrayList;
import java.awt.Point;

public class EWwall implements Tile
{
    private Point location;
    private ArrayList<Tile> list;

    public EWwall(Point p, ArrayList<Tile> l)
    {
        list = l;
        location = p;

    }

    @Override
    public ArrayList<Tile> contains() {
        // TODO Auto-generated method stub
        return list;
    }

    @Override
    public Point getLocation() {
        // TODO Auto-generated method stub
        return location;
    }



}
