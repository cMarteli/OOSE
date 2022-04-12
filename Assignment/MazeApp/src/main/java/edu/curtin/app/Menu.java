/**
 * UserInteface.java
 * Contains Menu TODO: actual maze
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

public class Menu
{


    /** Displays Menu */
    public static void showMenu()
    {
        boolean done = false;
        while(!done)
        {
            System.out.println("(1) Play (2) Help (0) Quit");

            try
            {
                switch(Integer.parseInt(Keyboard.nextLine()))
                {
                    case 1:
                        System.out.print("STUB! PLAY ");

                        break;

                    case 2:
                        System.out.print("STUB! HELP");

                        break;

                    case 0:
                        done = true;
                        break;

                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // if not a numerical value.
                System.out.println("Must be a number");
            }
        }
    }

}
