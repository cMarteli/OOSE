/**
 * @class ColourKey.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public class Key extends ColourTile
{

    public Key(int inX, int inY, int clr)
    {
        setX(inX);
        setY(inY);
        setClr(clr);
    }

    @Override
    public Object getContent()
    {
        return "Key";
    }

    @Override
    public void setContent(Object o)
    {
       System.out.println("Not applicable");
    }





}
