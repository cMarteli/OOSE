/**
 * Door.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public class Door extends ColourTile
{
    private String orientation;

    public Door(int inX, int inY, int clr, String o)
    {
        setX(inX);
        setY(inY);
        orientation = o;
        setClr(clr);
    }



    @Override
    public Object getContent()
    {
        return orientation;
    }

    @Override
    public void setContent(Object o)
    {
        orientation = o.toString();
    }



}