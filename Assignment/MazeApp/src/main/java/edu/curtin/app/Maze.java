/**
 * Maze.java
 * Handles Maze drawing methods
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import java.util.List;
//import java.util.Map;
import java.awt.Point;

public class Maze extends Graphics
{
    private static int rows;
    private static int columns;
    private String[][] maze;
    public List<Point> vWalls; //TODO: delete
    public List<Point> hWalls; //TODO: delete
    //Constructor
    public Maze(int x, int y, List<Point> verWalls, List<Point> horWalls)
    {
        vWalls = verWalls;
        hWalls = horWalls;

        rows = x;
        columns = y;
        maze = new String[rows][columns];

    }

    /************************************************************
    * IMPORT: none
    * EXPORT: void
    * ASSERTION: Display maze and update visual feed
    ************************************************************/
    public void displayMaze()
    {
        //System.out.print(CLEAR);
        printPadding(EDG_HOR, CNR_TOP_L, CNR_TOP_R); //Top padding
        Point tempVWall = new Point(); //temp vertical wall to check
        Point tempHWall = new Point(); //temp horizontal wall to check

        for(int x = 0; x < rows; x++)
        {
            System.out.println(); //new line
            System.out.print(EDG_VER); //Left Edge
            for(int y = 0; y < columns; y++)
            {
                tempVWall.setLocation(x, y);
                if(vWalls.contains(tempVWall)) //if first column OR contains wall print a wall to left
                {
                    System.out.print(EDG_VER + maze[x][y] + " ");
                }
                else
                {
                    System.out.print(" " + maze[x][y] + " ");//prints each cell
                }

            }
            System.out.print(EDG_VER); //Right Edge
            System.out.println(); //jumps line
            System.out.print(EDG_VER); //horizontal wall line starts here
            for(int y = 0; y < columns; y++)
            {
                tempHWall.setLocation(x+1, y); //sets and translates to x+1 since it needs to printed north
                if(hWalls.contains(tempHWall)) //if contains point
                {
                    System.out.print(WALL_HOR);//prints horizontal wall
                }
                else
                {
                    System.out.print("   ");
                }
            }
            System.out.print(EDG_VER);

        }

        System.out.println();

        printPadding(EDG_HOR, CNR_BTM_L, CNR_BTM_R); //Bottom
        System.out.println();


    }

    /************************************************************
    * IMPORT: symbol to draw (String)
    * EXPORT: void
    * ASSERTION:  //TODO: corners
    ************************************************************/
    // private void printRow()
    // {
    //     Point w = new Point();
    //     System.out.print(EDG_VER);
    //     for(int y = 0; y < columns; y++)
    //     {
    //         w.setLocation(x, y);
    //         if(vWalls.contains(w)) //if contains point
    //         {
    //             System.out.print(WALL_HOR);//prints horizontal wall
    //         }
    //         else
    //         {
    //             System.out.print("   ");
    //         }
    //     }
    //     System.out.print(EDG_VER);

    // }

    /************************************************************
    * IMPORT: symbol to draw (String)
    * EXPORT: void
    * ASSERTION: prints roof or base of maze array //TODO: corners
    ************************************************************/
    private void printPadding(String wall, String cnrL, String cnrR)
    {
        System.out.print(cnrL);
        for(int i = 1; i <= columns*3; i++)
        {
            System.out.print(wall);  //TODO: corners
        }
        System.out.print(cnrR);

    }

    /************************************************************
    * IMPORT: coordinate (Point), symbol to draw (String)
    * EXPORT: void
    * ASSERTION: Edits a single cell of the maze array
    ************************************************************/
    private void drawCell(Point p, String s)
    {
        maze[(int)p.getX()][(int)p.getY()] = s;
    }


    /************************************************************
    * IMPORT: coordinate (Point), symbol to draw (String)
    * EXPORT: void
    * ASSERTION: Fills maze with item data, updates player character
    ************************************************************/
    public void updateMaze(Point p)
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                maze[i][j] = " "; //default blank square
                drawCell(p,PLAYER_SYMBOL); //Cursor
                //add other specific things to fill in maze here
            }
        }
        displayMaze(); //displays

    }

    /************************************************************
    ACCESSORS
    ************************************************************/
    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public List<Point> getVWalls()
    {
        return vWalls;
    }

    public List<Point> getHWalls()
    {
        return hWalls;
    }

}
