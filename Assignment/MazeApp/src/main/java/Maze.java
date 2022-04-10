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
    //public static final int columns;
    //public static final int rows;
    //private Map<Point , Object> maze; //TODO temporary needs to change object to class block
    //Constructor
    public Maze(int x, int y)
    {
        //columns = x;
        //rows = y;
        String[][] newMaze = new String[x][y];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < x; j++)
            {
                newMaze[i][j] = "[   ]"; //default blank square
                newMaze[0][3] = "[ P ]"; //TODO: DEBUG ONLY
                //add other specific things to fill in maze here
            }
        }
        displayMaze(newMaze);


    }
    /*
    * Method to display maze and update visual feed
    */
    private void displayMaze(String[][] currMaze)
    {
        for(int i = 0; i < currMaze.length; i++)
        {
            System.out.println();
            for(int j = 0; j < currMaze.length; j++)
            {
                System.out.print(currMaze[i][j] + "\t"); //might not need the tab
            }

        }

    }

}
