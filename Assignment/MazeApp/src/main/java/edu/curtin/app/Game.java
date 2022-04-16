/**
 * Game.java
 * handles game initialization player controller
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import java.util.InputMismatchException;
import java.awt.Point;

public class Game
{
    // private static Map<Point, Tile> mazeMap; , HashMap<Point, Tile> map

    Maze gameMaze;
    Player player;

    //constructor
    public Game(Maze m)
    {
        gameMaze = m;
        player = new Player(gameMaze, new Point(0,0)); //TODO: get player vars from file reader not here

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
