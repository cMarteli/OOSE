/**
 * KeyRing.java
 * Default empty key ring for player char
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.awt.Point;

public class Maze
{
    private static int rows;
    private static int columns;
    private String[][] maze;
    public Point cursor;
    public Point testWall; //TODO: delete
    public List<Point> vWalls; //TODO: delete
    //Constructor
    public Maze(int x, int y)
    {
        rows = x;
        columns = y;
        maze = new String[rows][columns];
        cursor = new Point(0,0);
        testWall = new Point(1,1);
        vWalls = new ArrayList<>();
        vWalls.add(testWall);


        updateMaze();
    }
    /*
    * Method to display maze and update visual feed
    */
    private void displayMaze(String[][] currMaze)
    {
        for(String[] i: currMaze)
        {
            System.out.println();
            for(String j : i)
            {
                System.out.print(j + "\t"); //might not need the tab
            }
        }
        System.out.println();

    }

    /*
    * Method to fill maze with new data, updates cursor
    */
    private void updateMaze()
    {
        //columns = x;
        //rows = y;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                maze[i][j] = "[   ]"; //default blank square
                maze[(int)testWall.getX()][(int)testWall.getY()] = "[|  ]"; //TODO: DEBUG ONLY
                maze[(int)cursor.getX()][(int)cursor.getY()] = "[ P ]"; //TODO: DEBUG ONLY
                if(testWall.getLocation().equals(cursor.getLocation()))
                {
                    maze[(int)cursor.getX()][(int)cursor.getY()] = "[|P ]";
                }

                //add other specific things to fill in maze here
            }
        }
        displayMaze(maze); //displays
    }

    /*
    * Method to move cursor in  direction parsed
    * n = north, s = south, e = east, w = west
    */
    public void moveCursor(char dir)
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
        updateMaze(); //TODO DEBUG ONLY
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
        if((int)cursor.getX()+1 < columns) //boundary check
        {
            p.translate(1, 0);
        }
    }

    private void moveLeft(Point p)
    {
        if((int)p.getY() > 0 && leftIsClear()) //boundary check
        {
            p.translate(0, -1);
        }
    }

    private void moveRight(Point p)
    {
        if((int)p.getY()+1 < rows && rightIsClear()) //boundary check
        {
            p.translate(0, 1);
        }
    }
    //if going right check if dest is on list if it is block
    //if going left check if current location is on list if so block
    private boolean leftIsClear() //TODO:
    {

        if(vWalls.contains(cursor))
        {
            System.out.println("collision - left wall");
            return false;

        }
        return true;
    }
        //if going right check if dest is on list if it is block
    //if going left check if current location is on list if so block

    private boolean rightIsClear()
    {
        Point dest = cursor.getLocation();
        dest.translate(0, 1);

        if(vWalls.contains(dest))
        {
            System.out.println("collision - right wall");
            return false;

        }
        return true;
    }

}
