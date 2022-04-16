/**
 * Tile.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import java.awt.Point;

//A special tyle is not repeatable
public class SpecialTile implements Tile
{
    private int x;
    private int y;
    private Point coordinate;
    private String type;

    public SpecialTile(int inX, int inY, String inType)
    {
        setX(inX);
        setY(inY);
        type = inType;
        setCoordinate(new Point(x,y));
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String getType()
    {
        return type;
    }

}
