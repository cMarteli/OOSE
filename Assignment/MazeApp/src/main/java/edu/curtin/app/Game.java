/**
 * Game.java
 * handles game initialization player controller
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import java.util.InputMismatchException;

public class Game
{
    private Maze gameMaze;
    private Player player;

    //constructor
    public Game(Maze m)
    {
        gameMaze = m;
        player = new Player(gameMaze);

    }

    public void checkTile() //checks if player is in a special tile
    {
        if(gameMaze.getTiles().containsKey(player.getLocation())) //if player's current tile is a message tile
        {
            System.out.println(gameMaze.getTiles().get(player.getLocation()).getContent().toString()); //Gets message from tile and prints it
        }
        // else if(gameMaze.getEnd().equals(player.getLocation()))
        // {
        //     System.out.println("GAME OVER!");
        // }
    }


    public void controller()
    {
        boolean done = false;
        while(!done)
        {
            checkTile(); //checks for messages
            System.out.println("Which direction? (N)orth (S)outh (E)ast (W)est \n(Q)uit");
            try
            {
                char input = Keyboard.next().toLowerCase().charAt(0);
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
