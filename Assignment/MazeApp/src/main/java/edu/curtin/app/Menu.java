/**
 * Menu.java
 * Displays user Menus and validates keyboard input
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * Some methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
 */
package edu.curtin.app;

import java.util.InputMismatchException;

public class Menu
{


    /************************************************************
    Displays menu and launches game
    ************************************************************/
    public static void showMenu()
    {
        boolean done = false;
        while(!done)
        {
            try
            {
                switch(checkInteger("(1) Play (2) Help (0) Quit"))
                {
                    case 1:
                        //TODO: ask for name of file to read here
                        String fileName = checkFileName(".txt");

                        System.out.print("Starting Game");
                        Game g = new Game(4, 6);//TODO Hardcoded change to obtained from file

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

    /************************************************************
    IMPORT: ext (String)
    EXPORT: filename (String)
    ASSERTION: Lets user enter the file name
    ************************************************************/
    public static String checkFileName(String ext)
    {
        String prompt = "Please enter a file name:", filename = "";
        int ans = 0;

        System.out.println(prompt);
        filename = Keyboard.next() + ext;
        prompt = "File name: <" + filename + ">\nIs this correct? [1]Confirm [2]Cancel\n";
        do
        {
            ans = checkInteger(prompt);
        }
        while(ans != 1 && ans != 2);

        if(ans == 1)
        {
            return filename;
        }
        else
        {
            return checkFileName(ext);
        }
    }

    /************************************************************
    IMPORT: prompt (String)
    EXPORT: userInt (integer)
    ASSERTION: Validator Method. Gets user integer and repeats until it's a valid input
    ************************************************************/
    public static int checkInteger(String prompt)
    {
        int userInt = 0;
        String error = "(Invalid Number)\n";
        String outStr = prompt;
        boolean isValid = false;

        while (isValid == false)
        {
            try
            {
                System.out.println(outStr);
                outStr = error + prompt;
                userInt = Keyboard.nextInt();
                isValid = true;
            }
            catch(InputMismatchException e)
            {
                System.out.print(error);
                userInt = 0;
                outStr = prompt;
                isValid = false;
            }
        }

        return userInt;
    }

}
