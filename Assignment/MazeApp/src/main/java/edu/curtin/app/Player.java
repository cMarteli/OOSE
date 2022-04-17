/**
 * Player.java
 * Controls and stores player's current location as well as boundary checks
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.awt.Point;
import java.util.Map;
public class Player
{
    private Point cursor; //stores player location
    //private int rows; //for boundary checking
    private int columns;
    private Map<Point, Wall> vWalls;
    private Map<Point, Wall> hWalls;



    /************************************************************
    CONSTRUCTOR
    ASSERTION: Sets starting location for player character
    ************************************************************/
    public Player(Maze m)
    {
        cursor = m.getStart();
        columns = m.getColumns();
        vWalls = m.getVWalls();
        hWalls = m.getHWalls();

        m.updateMaze(cursor);

    }

    /************************************************************
    ACCESSOR
    ASSERTION: Returns current location
    ************************************************************/
    public Point getLocation()
    {
        return cursor;
    }


    /************************************************************
    Moves cursor in  direction parsed
    ASSERTION: Sets starting location for player character
    ************************************************************/
    public void moveCursor(char dir, Maze m)
    {
        if(dir == 'N' || dir == 'n')
        {
            System.out.println("Moving cursor north");
            moveUp(cursor);
        }
        else if(dir == 'S' || dir == 's')
        {
            System.out.println("Moving cursor south");
            moveDown(cursor);
        }
        else if(dir == 'E' || dir == 'e')
        {
            System.out.println("Moving cursor east");
            moveRight(cursor);
        }
        else if(dir == 'W' || dir == 'w')
        {
            System.out.println("Moving cursor west");
            moveLeft(cursor);
        }
        m.updateMaze(cursor);
    }

    private void moveUp(Point p)
    {
        if(upIsClear()) //boundary check
        {
            p.translate(-1, 0);
        }
        else
        {
            System.out.println("collision - north wall");
        }
    }

    private void moveDown(Point p)
    {
        if(downIsClear()) //boundary check
        {
            p.translate(1, 0);
        }
        else
        {
            System.out.println("collision - south wall");
        }
    }

    private void moveLeft(Point p)
    {
        if(leftIsClear()) //boundary check
        {
            p.translate(0, -1);
        }
        else
        {
            System.out.println("collision - west wall");
        }
    }

    private void moveRight(Point p)
    {
        if(rightIsClear()) //boundary check
        {
            p.translate(0, 1);
        }
        else
        {
            System.out.println("collision - east wall");
        }
    }


    //Check if current location is not on vertical wall list AND Y-coordinate is greater than zero
    private boolean leftIsClear()
    {
        return (!vWalls.containsKey(cursor) && ((int)cursor.getY() > 0)); //wall check && outside boundary check
    }

    //Check if destination is not on vertical wall list AND Y-coordinate(+1) is greater than max number of columns
    private boolean rightIsClear()
    {
        Point dest = cursor.getLocation();
        dest.translate(0, 1);

        return(!vWalls.containsKey(dest) && ((int)cursor.getY()+1 < columns));
    }

    //Check if current location is not on horizontal wall list AND X-coordinate is greater than zero
    private boolean upIsClear()
    {
        return (!hWalls.containsKey(cursor) && ((int)cursor.getX() > 0)); //wall check && outside boundary check
    }

    //Check if destination is not on vertical wall list AND X-coordinate(+1) is greater than max number of rows
    private boolean downIsClear()
    {
        Point dest = cursor.getLocation();
        dest.translate(1, 0);

        return(!hWalls.containsKey(dest) && ((int)cursor.getX()+1 < columns));
    }



}
