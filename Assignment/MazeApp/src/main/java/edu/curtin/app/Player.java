/**
 * Player.java
 * Controls and stores player's current location as well as boundary checks
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import java.awt.Point;
import java.util.List;
public class Player
{
    private Point cursor; //stores player location
    private int rows; //for boundary checking
    private int columns;
    private List<Point> vWalls, hWalls;



    /************************************************************
    CONSTRUCTOR
    ASSERTION: Sets starting location for player character
    ************************************************************/
    public Player(Maze m, Point start)
    {
        cursor = start;
        rows = m.getRows();
        columns = m.getColumns();
        vWalls = m.getVWalls();
        hWalls = m.getHWalls();

        m.updateMaze(start);

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
        if((int)p.getX() > 0) //boundary check
        {
            p.translate(-1, 0);
        }
    }

    private void moveDown(Point p)
    {
        if((int)cursor.getX()+1 < rows) //boundary check
        {
            p.translate(1, 0);
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
            System.out.println("collision - left wall");
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
            System.out.println("collision - right wall");
        }
    }


    //Check if current location is not on vertical wall list AND Y-coordinate is greater than zero
    private boolean leftIsClear()
    {
        return (!vWalls.contains(cursor) && ((int)cursor.getY() > 0)); //wall check && outside boundary check
    }

    //Check if destination is not on vertical wall list AND Y-coordinate(+1) is greater than max number of columns
    private boolean rightIsClear()
    {
        Point dest = cursor.getLocation();
        dest.translate(0, 1);

        return(!vWalls.contains(dest) && ((int)cursor.getY()+1 < columns));

    }







}
