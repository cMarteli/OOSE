/**
 * Tile.java
 * Using template method pattern
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public abstract class Tile
{

    //Fields related to Tile location
    private int x;
    private int y;

    /*** ACCESSORS  ***/

    /* default */ final int getX() {
        return x;
    }
    /* default */ final int getY() {
        return y;
    }

    public abstract Object getContent();

    /*** MUTATORS  ***/

    /* default */ final void setY(int y) {
        this.y = y;
    }

    /* default */ final void setX(int x) {
        this.x = x;
    }

    public abstract void setContent(Object o);



}
