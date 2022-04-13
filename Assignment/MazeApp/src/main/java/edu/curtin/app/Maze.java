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
        printPadding("___"); //TODO: HARDCODED

        for(int x = 0; x < rows; x++)
        {
            System.out.println(); //new row
            for(int y = 0; y < columns; y++)
            {
                if(y == 0)
                {
                    System.out.print("|" + maze[x][y]);//if last column print a wall
                }
                else if(y == columns-1)
                {
                    System.out.print(maze[x][y] + "|");//if last column print a wall
                }
                else
                {
                    System.out.print(maze[x][y]);//prints each cell
                }

            }
        }
        System.out.println();

        printPadding("```"); //TODO: HARDCODED
        System.out.println();


    }

    /************************************************************
    * IMPORT: symbol to draw (String)
    * EXPORT: void
    * ASSERTION: prints roof or base of maze array //TODO: corners
    ************************************************************/
    private void printPadding(String symbol)
    {
        System.out.print(" ");
        for(int i = 1; i <= columns; i++)
        {
            System.out.print(symbol);  //TODO: corners
        }

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
                maze[i][j] = "   "; //default blank square
                drawCell(p, " " + PLAYER_SYMBOL + " "); //Cursor
                //add other specific things to fill in maze here
            }
        }
        //draw walls to maze array
        for (Point w : vWalls)
        {
            drawCell(w, "|  ");
            if(w.equals(p)) //checks if player is overlapping wall
            {
                drawCell(w, "|"+PLAYER_SYMBOL + " ");
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

    public List<Point> getWalls()
    {
        return vWalls;
    }

}
