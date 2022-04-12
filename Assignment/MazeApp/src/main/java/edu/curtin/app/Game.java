/**
 * Game.java
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Game
{
    private static Map<Point, Tile> mazeMap;

    //constructor
    public Game()
    {
        mazeMap = new HashMap<>();
    }


}
