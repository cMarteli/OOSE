/**
 * Game.java
 * handles game initialization
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

public class Game
{
    // private static Map<Point, Tile> mazeMap; , HashMap<Point, Tile> map

    Maze gameMaze;
    Player player;

    //constructor
    public Game()
    {
        Point playerStart = new Point(0,0); //TODO: get this from file reader not here
        List<Point> vWalls = new LinkedList<>(); //get this from file reader not here
        List<Point> hWalls = new LinkedList<>(); //get this from file reader not here
        //TODO: get this from file reader not here
        vWalls.add(new Point(0,1));
        vWalls.add(new Point(0,3));
        vWalls.add(new Point(1,3));
        vWalls.add(new Point(2,1));
        vWalls.add(new Point(3,2));

        hWalls.add(new Point(1,1));
        hWalls.add(new Point(2,0));
        hWalls.add(new Point(2,2));
        hWalls.add(new Point(3,2));

        gameMaze = new Maze(4, 4, vWalls, hWalls); //TODO: get this from file reader not here
        player = new Player(gameMaze, playerStart);

        controller();


    }


    public void controller()
    {
        boolean done = false;
        char input  = ' ';
        while(!done)
        {
            System.out.println("Which direction? (N)orth (S)outh (E)ast (W)est \n(Q)uit");
            try
            {
                input = Keyboard.next().toLowerCase().charAt(0);
                switch (input) {
                    case 'n':
                        player.moveCursor('n', gameMaze);
                        break;

                    case 's':
                        player.moveCursor('s', gameMaze);
                        break;

                    case 'e':
                        player.moveCursor('e', gameMaze);
                        break;

                    case 'w':
                        player.moveCursor('w', gameMaze);
                        break;

                    case 'q':
                        done = true;
                        break;

                    default:
                    System.out.println("Invalid input");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }


        }

    }


}
