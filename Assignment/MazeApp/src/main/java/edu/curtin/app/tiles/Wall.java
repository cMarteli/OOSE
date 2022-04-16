/**
 * NSwall.java
 * North -> South wall
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public class Wall implements Tile
{
    private int orientation; //0 - horizontal, 1 - Vertical

    public Wall(int o)
    {
        orientation = o;
    }

    @Override
    public String getValue() {
        if(orientation == 0)
        {
            return "Horizontal Wall";
        }
        else
        {
            return "Vertical Wall";
        }
    }

    @Override
    public void setValue(String s) {
        // TODO Auto-generated method stub

    }



}
