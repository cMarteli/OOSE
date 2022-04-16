/**
 * Maze.java
 * Handles Maze drawing methods
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;


import java.util.HashMap;

import java.awt.Point;

public class Maze extends Graphics
{
    private int rows;
    private int columns;
    private String[][] maze; //Maze to draw to CLI
    private HashMap<String, SpecialTile> sTiles; //start, end and dimension values
    private HashMap<Point, Wall> vWalls; // Vertical wall list
    private HashMap<Point, Wall> hWalls; // Horizontal wall list
    //Constructor
    public Maze(HashMap<String, SpecialTile> spTiles, HashMap<Point, Wall> verWalls, HashMap<Point, Wall> horWalls)
    {
        vWalls = verWalls;
        hWalls = horWalls;
        sTiles = spTiles;

        //DEBUG
        //System.out.println("Rows: " + getRows() + "Columns: " + getColumns());
        //System.out.println("Start: " + getStart().toString() + "End: " + getEnd().toString());
        rows = getRows();
        columns = getColumns();

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
        printTopRow(); //Top padding
        Point tempVWall = new Point(); //temp vertical wall to check
        Point tempHWall = new Point(); //temp horizontal wall to check

        //** OBJECT AND PLAYER LINE CREATOR */
        for(int x = 0; x < rows; x++)
        {
            System.out.println(); //new line
            System.out.print(EDG_VER); //Left Edge - vert wall line start
            for(int y = 0; y < columns; y++)
            {
                tempVWall.setLocation(x, y);
                if(vWalls.containsKey(tempVWall)) //if first column OR contains wall print a wall to left
                {
                    System.out.print(EDG_VER + maze[x][y] + " ");
                }
                else
                {
                    System.out.print(" " + maze[x][y] + " ");//prints each cell
                }

            }
            System.out.print(EDG_VER); //Right Edge

            //** H WALL AND H DOOR LINE CREATOR */
            if(x != rows-1) //checks if final line if so skips it
            {
                System.out.println(); //jumps line
                tempHWall.setLocation(x+1, 0);
                if(hWalls.containsKey(tempHWall)) // checks if left edge needs to a join tile
                {
                    System.out.print(JOIN_L);
                }
                else
                {
                    System.out.print(EDG_VER); //Left edge - horizontal wall line start
                }

                for(int y = 0; y < columns; y++)
                {

                    tempHWall.setLocation(x+1, y); //sets and translates to x+1 since it needs to printed north
                    if(hWalls.containsKey(tempHWall)) //if contains point
                    {
                        System.out.print(WALL_HOR);//prints horizontal wall
                    }
                    else if(hWalls.containsKey(tempVWall.getLocation()))
                    {

                    }
                    else
                    {
                        System.out.print("   ");
                    }

                }

                System.out.print(EDG_VER); //Right edge - line end TODO:Left side joins
            }

        }

        System.out.println();
        printBottomRow(); //Bottom
        System.out.println();
    }

    /************************************************************
    * IMPORT: symbol to draw (String)
    * EXPORT: void
    * ASSERTION: prints roof of maze array
    ************************************************************/
    private void printTopRow()
    {
        Point tempW = new Point();
        System.out.print(CNR_TOP_L);
        for(int i = 0; i < columns; i++)
        {
            tempW.setLocation(0, i);
            if(vWalls.containsKey(tempW)) //checks if there's a wall below
            {
                System.out.print(JOIN_TOP + EDG_HOR + EDG_HOR);

            }
            else
            {
                System.out.print(EDG_HOR + EDG_HOR + EDG_HOR);
            }
        }
        System.out.print(CNR_TOP_R);
    }

    /************************************************************
    * IMPORT: symbol to draw (String)
    * EXPORT: void
    * ASSERTION: prints roof or base of maze array //TODO: corners
    ************************************************************/
    private void printBottomRow()
    {
        Point tempW = new Point();
        System.out.print(CNR_BTM_L);
        for(int i = 0; i < columns; i++)
        {
            tempW.setLocation(rows-1, i);
            if(vWalls.containsKey(tempW)) //checks if there's a wall below
            {
                System.out.print(JOIN_BTM + EDG_HOR + EDG_HOR);

            }
            else
            {
                System.out.print(EDG_HOR + EDG_HOR + EDG_HOR);
            }
        }
        System.out.print(CNR_BTM_R);
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
        return sTiles.get("dimension").getX();
    }

    public int getColumns()
    {
        return sTiles.get("dimension").getY();
    }

    public Point getStart()
    {
        return sTiles.get("start").getCoordinate();
    }

    public Point getEnd()
    {
        return sTiles.get("end").getCoordinate();
    }

    public HashMap<Point, Wall> getVWalls()
    {
        return vWalls;
    }

    public HashMap<Point, Wall> getHWalls()
    {
        return hWalls;
    }

}
