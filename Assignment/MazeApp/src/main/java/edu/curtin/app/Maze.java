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
    //Constructor
    public Maze(int x, int y, List<Point> walls)
    {
        vWalls = walls;

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
        Point tempWall = new Point(); //temp vertical wall to check

        for(int x = 0; x < rows; x++)
        {
            System.out.println();
            System.out.print(EDG_VER); //Left Edge
            for(int y = 0; y < columns; y++)
            {
                tempWall.setLocation(x, y);
                if(vWalls.contains(tempWall)) //if first column OR contains wall print a wall to left
                {
                    System.out.print(EDG_VER + maze[x][y] + " ");
                }
                else
                {
                    System.out.print(" " + maze[x][y] + " ");//prints each cell
                }

            }
            System.out.print(EDG_VER); //Right Edge
        }

        System.out.println();

        printPadding(EDG_HOR, CNR_BTM_L, CNR_BTM_R); //Bottom
        System.out.println();


    }

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
    * ASSERTION: Fills maze with data, updates cursor
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
        //draw walls to maze array
        // for (Point w : vWalls)
        // {
        //     drawCell(w, "|  ");
        //     if(w.equals(p)) //checks if player is overlapping wall
        //     {
        //         drawCell(w, "|"+PLAYER_SYMBOL + " ");
        //     }

        // }
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

    public List<Point> getWalls()
    {
        return vWalls;
    }

}
