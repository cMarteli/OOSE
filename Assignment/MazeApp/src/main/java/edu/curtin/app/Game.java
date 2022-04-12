/**
 * Game.java
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.util.HashMap;
//import java.util.Map;
import java.awt.Point;

public class Game
{
    // private static Map<Point, Tile> mazeMap; , HashMap<Point, Tile> map
    // private static int rows;
    // private static int columns;


    //constructor
    public Game(int r, int c)
    {
        Maze m = new Maze(r, c);
        m.moveCursor('s');
        m.moveCursor('e');
        m.moveCursor('s');
        m.moveCursor('e');
        m.moveCursor('n');
        m.moveCursor('w');
        m.moveCursor('w');
        m.moveCursor('n');
        m.moveCursor('w');
        m.moveCursor('s');
    }


}
