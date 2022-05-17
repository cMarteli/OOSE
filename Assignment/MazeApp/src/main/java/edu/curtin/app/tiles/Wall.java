/**
 * NSwall.java
 * North -> South wall
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public class Wall extends Tile
{
    private String content;

    public Wall(int inX, int inY, String s)
    {
        setX(inX);
        setY(inY);
        content = s;
    }

    @Override
    public void setContent(Object s)
    {
        content = s.toString();
    }

    @Override
    public String getContent()
    {
        return content;
    }

}
