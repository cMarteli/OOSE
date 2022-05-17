/**
 * Game.java
 * handles game initialization player controller
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
import java.util.InputMismatchException;
import edu.curtin.app.tiles.Door;
import edu.curtin.app.tiles.Key;

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

        if(gameMaze.getKeys().containsKey(player.getLocation())) //checks if player is standing on a key
        {
            Key k = gameMaze.getKeys().get(player.getLocation());
            System.out.println("Obtained a "+ k.getClr() +" Key!");
            gameMaze.getKeys().remove(player.getLocation()); //remove key from map
            unlockDoors(k);
        }
    }

    //unlocks all doors of the the colour corresponding to key
    public void unlockDoors(Key k)
    {
        for(Door d : gameMaze.getDoors().values())
        {
            if(d.keyMatches(k))
            {
                System.out.println("Unlocked all doors of " + k.getClr() + " colour.");
                d.setLockStatus(false);
            }

        }
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
