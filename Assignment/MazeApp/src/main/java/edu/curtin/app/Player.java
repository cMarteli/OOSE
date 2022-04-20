/**
 * Player.java
 * Controls and stores player's current location as well as boundary checks
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import java.awt.Point;
import java.util.logging.Logger;
public class Player
{
    private Point cursor; //stores player location
    private int columns; //for boundary checking

     /**
     * Logger from MazeApp.java
     */
    private final static Logger LOGR = Logger.getLogger(MazeApp.class.getName());



    /************************************************************
    CONSTRUCTOR
    IMPORT: m (Maze)
    ASSERTION: Sets starting location for player character and draws maze
    ************************************************************/
    public Player(Maze m)
    {
        cursor = m.getStart();
        columns = m.getColumns();
        m.updateMaze(cursor);
    }

    /************************************************************
    Moves cursor in  direction parsed
    ASSERTION: Sets starting location for player character
    ************************************************************/
    public void moveCursor(char dir, Maze m)
    {
        if(dir == 'N' || dir == 'n')
        {
            LOGR.info("Moving cursor north");
            moveUp(cursor, m);
        }
        else if(dir == 'S' || dir == 's')
        {
            LOGR.info("Moving cursor south");
            moveDown(cursor, m);
        }
        else if(dir == 'E' || dir == 'e')
        {
            LOGR.info("Moving cursor east");
            moveRight(cursor, m);
        }
        else if(dir == 'W' || dir == 'w')
        {
            LOGR.info("Moving cursor west");
            moveLeft(cursor, m);
        }
        m.updateMaze(cursor);
    }

    /************************************************************
    Helper methods for moveCursor()
    ASSERTION: move point on corresponding axis
    ************************************************************/
    private void moveUp(Point p, Maze m)
    {
        if(upIsClear(m)) //boundary check
        {
            p.translate(-1, 0);
        }
        else
        {
            LOGR.info("collision - north wall");
        }
    }

    private void moveDown(Point p, Maze m)
    {
        if(downIsClear(m)) //boundary check
        {
            p.translate(1, 0);
        }
        else
        {
            LOGR.info("collision - south wall");
        }
    }

    private void moveLeft(Point p, Maze m)
    {
        if(leftIsClear(m)) //boundary check
        {
            p.translate(0, -1);
        }
        else
        {
            LOGR.info("collision - west wall");
        }
    }

    private void moveRight(Point p, Maze m)
    {
        if(rightIsClear(m)) //boundary check
        {
            p.translate(0, 1);
        }
        else
        {
            LOGR.info("collision - east wall");
            //System.out.println("collision - east wall");
        }
    }

    /************************************************************
    Validator methods for moveCursor()
    ASSERTION: Check boundaries for collision
    ************************************************************/
    //Check if current location is not on vertical wall list AND Y-coordinate is greater than zero
    private boolean leftIsClear(Maze m)
    {
        return (!m.getVWalls().containsKey(cursor) && ((int)cursor.getY() > 0) &&
        doorClear(cursor, m, "VERTICAL")); //wall check && outside boundary check
    }

    //Check if destination is not on vertical wall list AND Y-coordinate(+1) is greater than max number of columns
    private boolean rightIsClear(Maze m)
    {
        Point dest = cursor.getLocation();
        dest.translate(0, 1);

        return(!m.getVWalls().containsKey(dest) && ((int)cursor.getY()+1 < columns) &&
        doorClear(dest, m, "VERTICAL"));
    }

    //Check if current location is not on horizontal wall list AND X-coordinate is greater than zero
    private boolean upIsClear(Maze m)
    {
        return (!m.getHWalls().containsKey(cursor) && ((int)cursor.getX() > 0) &&
        doorClear(cursor, m, "HORIZONTAL")); //wall check && outside boundary check
    }

    //Check if destination is not on vertical wall list AND X-coordinate(+1) is greater than max number of rows
    private boolean downIsClear(Maze m)
    {
        Point dest = cursor.getLocation();
        dest.translate(1, 0);

        return(!m.getHWalls().containsKey(dest) && ((int)cursor.getX()+1 < columns) &&
         doorClear(dest, m, "HORIZONTAL"));
    }


    //if theres a door and it's locked return false else return true
    private boolean doorClear(Point p, Maze m, String orientation)
    {
        if(m.getDoors().containsKey(p)) //if there's a door at player
        {
            if(m.getDoors().get(p).getOrientation().equals(orientation)) //if it's the same orientation
            return !m.getDoors().get(p).isLocked(); //return if clear status
        }

        return true;


    }

    /************************************************************
    ACCESSORS
    ************************************************************/
    public Point getLocation()
    {
        return cursor;
    }


}
