/**
 * Menu.java
 * Displays user Menus and validates keyboard input
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * May contain methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
 */
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.util.Map;
import java.util.HashMap;
import java.awt.Point;

public class Menu extends Graphics
{

    public static Map<Point, Tile> map; //TODO


    /************************************************************
    * IMPORT: none
    * EXPORT: void
    * ASSERTION: Displays menu, launches game and loads input
    ************************************************************/
    public static void showMenu()
    {
        boolean fileLoaded = false;
        boolean done = false;
        while(!done)
        {
            switch(checkInteger(GREEN +"(1) Play "+ CYAN +"(2) Load File "+ YELLOW +"(3) Help "+ RED +"(0) Quit"+ RESET))
            {
                case 1://Option Play
                    if(!fileLoaded) //TODO: Temporarily disabled. remove '!'
                    {
                        play();//game start TODO Parse Object from file reader here
                    }
                    else
                    {
                        System.out.println("Please load an input file first!");
                    }
                    break;

                case 2: //Option load file

                    fileLoaded = loadFile();
                    break;

                case 3:
                    System.out.print("Testing");
                    gfxTest(); //DEBUG

                    break;

                case 0:
                    done = true;
                    break;

                default:
                    System.out.println("Enter a number");
                    break;
            }

        }
    }

    /************************************************************
    IMPORT: none
    EXPORT: void
    ASSERTION: Starts game - only runs if file loaded = true
    ************************************************************/
    private static void play()
    {
        System.out.println("Starting Game");
        //TODO Parse Object from file reader here
        Game g = new Game();//TODO Parse Object from file reader here
    }

    /************************************************************
    IMPORT:
    EXPORT: boolean (success status of load)
    ASSERTION: Loads file using FileReader class
    ************************************************************/
    private static boolean loadFile()
    {
        map = FileReader.readFile(checkFileName(".txt"));
        //TODO: call filereader method
        return true;//change to return success or fail

    }

    /************************************************************
    IMPORT: file extension (String)
    EXPORT: filename (String)
    ASSERTION: Lets user enter the file name and validates input
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
        boolean isValid = false;

        while (!isValid)
        {
            try
            {
                System.out.println(prompt);
                userInt = Integer.parseInt(Keyboard.nextLine());
                isValid = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Enter a number");
            }
        }

        return userInt;
    }

}
