/**
 * KeyRing.java
 * Default empty key ring for player char
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import java.util.Map;
import java.awt.Point;

public class Maze
{
    private static int columns;
    private static int rows;
    private String[][] maze;
    public Point cursor;
    //Constructor
    public Maze(int x, int y)
    {
        columns = x;
        rows = y;
        maze = new String[columns][rows];
        cursor = new Point(0,0);

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
        for(int i = 0; i < columns; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                maze[i][j] = "[   ]"; //default blank square
                maze[(int)cursor.getY()][(int)cursor.getX()] = "[ P ]"; //TODO: DEBUG ONLY
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
        boolean moved = false;
        if(dir == 'N' || dir == 'n')
        {
            if((int)cursor.getY() > 0) //boundary check
            {
                System.out.println("Moving cursor north");
                cursor.translate(0, -1);
                moved = true;
            }

        }
        else if(dir == 'S' || dir == 's')
        {
            if((int)cursor.getY()+1 < columns) //boundary check
            {
                System.out.println("Moving cursor south");
                cursor.translate(0, 1);
                moved = true;
            }
        }
        else if(dir == 'E' || dir == 'e')
        {
            if((int)cursor.getX()+1 < rows) //boundary check
            {
                System.out.println("Moving cursor east");
                cursor.translate(1, 0);
                moved = true;
            }
        }
        else if(dir == 'W' || dir == 'w')
        {
            if((int)cursor.getX() > 0) //boundary check
            {
                System.out.println("Moving cursor west");
                cursor.translate(-1, 0);
                moved = true;
            }
        }
        if(moved)
        {
            updateMaze();
        }
        else
        {
            System.out.println("Out of bounds");
        }

    }

}
