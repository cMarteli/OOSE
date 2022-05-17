/**
 * Tile.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

//A special tile is not repeatable
public class MessageTile extends Tile
{
    private String content;

    public MessageTile(int inX, int inY, String s)
    {
        setX(inX);
        setY(inY);
        content = s;
    }

    @Override
    public void setContent(Object s)
    {
        content += ", " + s.toString();
    }

    @Override
    public String getContent()
    {
        return content;
    }

}
