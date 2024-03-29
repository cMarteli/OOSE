/**
 * Maze.java
 * Handles Maze drawing methods
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import edu.curtin.app.tiles.*;

import static edu.curtin.app.Graphics.*; //imports GFX class
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Maze
{
    private int rows;
    private int columns;
    private Point startP;
    private Point endP;
    private String[][] maze; //Maze to draw to CLI
    private Map<Point, Wall> vWalls; // Vertical wall list
    private Map<Point, Wall> hWalls; // Horizontal wall list
    private Map<Point, Door> doors; // Vertical wall list
    private Map<Point, Key> keys; // Vertical wall list


    private Map<Point, Tile> sTiles; // Special tile list

    //Constructor
    public Maze(int x, int y)
    {
        rows = x;
        columns = y;
        setVertWalls(new HashMap<Point, Wall>());
        setHoriWalls(new HashMap<Point, Wall>());
        setTiles(new HashMap<Point, Tile>());
        setDoors(new HashMap<Point, Door>());
        setKeys(new HashMap<Point, Key>());
        maze = new String[rows][columns]; //creates maze
    }



    /************************************************************
    * IMPORT: none
    * EXPORT: void
    * ASSERTION: Display maze and update visual feed
    ************************************************************/
    public void displayMaze()
    {
        System.out.print(CLEAR+RESET_CURSOR); //clears screen

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
                else if(isDoor(tempVWall, "VERTICAL")) //if vert DOOR
                {
                    System.out.print(doors.get(tempVWall).getContent().toString() + maze[x][y] + " ");
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
                    else if(isDoor(tempHWall, "HORIZONTAL")) //if horizontal door
                    {
                        String symbol = doors.get(tempHWall).getContent().toString();
                        System.out.print(" " + symbol + symbol);
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
    private void fillCell(Point p, String s)
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
                drawKeys(); // draws any keys on map
                fillCell(p,PLAYER_SYMBOL); //Cursor
            }
        }
        displayMaze(); //displays
    }

    private void drawKeys()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                Point p = new Point(i,j);
                if(keys.containsKey(p))
                {
                    fillCell(p, keys.get(p).getContent().toString()); //Colours key
                }
            }
        }
    }


    //checks if door needs to be printed
    //Point: coordinate, String Orientation
    private boolean isDoor(Point p, String s)
    {
        if(doors.containsKey(p))
        {
            return doors.get(p).getOrientation().equals(s);
        }
        else
        {
            return false;
        }
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

    public Point getStart()
    {
        return startP;
    }

    public Point getEnd()
    {
        return endP;
    }

    public Map<Point, Wall> getVWalls()
    {
        return vWalls;
    }

    public Map<Point, Wall> getHWalls()
    {
        return hWalls;
    }

    public Map<Point, Tile> getTiles() {
        return sTiles;
    }

    /************************************************************
    MUTATORS
    ************************************************************/
    public void addHoriWalls(int x, int y)
    {
        hWalls.put(new Point(x, y), new Wall(x,y,"HORIZONTAL"));
    }

    public void addVertWalls(int x, int y)
    {
        vWalls.put(new Point(x, y),  new Wall(x,y,"VERTICAL"));
    }

    public void addTiles(int x, int y, String m)
    {
        Point p = new Point(x, y);
        if(sTiles.containsKey(p)) //if tile already exists add message
        {
            sTiles.get(p).setContent(m);
        }
        else //if tile doesn't exist create one
        {
            sTiles.put(p, new MessageTile(x, y, m));

        }
    }

    public void addKeys(int x, int y, int clr)
    {
        keys.put(new Point(x, y), new Key(x, y, clr));
    }

    public void addDoors(int x, int y, int clr, String orientation)
    {
        doors.put(new Point(x, y),  new Door(x, y, clr, orientation));
    }

    public void setVertWalls(Map<Point, Wall> w)
    {
        vWalls = w;
    }

    public void setHoriWalls(Map<Point, Wall> w)
    {
        hWalls = w;
    }

    public void setStart(int x, int y)
    {
        startP = new Point(x,y);
    }

    public void setEnd(int x, int y)
    {
        endP = new Point(x,y);
    }

    public void setTiles(Map<Point, Tile> t) {
        sTiles = t;
    }

    public Map<Point, Door> getDoors() {
        return doors;
    }

    public void setDoors(Map<Point, Door> doors) {
        this.doors = doors;
    }

    public Map<Point, Key> getKeys() {
        return keys;
    }

    public void setKeys(Map<Point, Key> keys) {
        this.keys = keys;
    }
}
