/**
 * Menu.java
 * Displays user Menus and validates keyboard input
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * May contain methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
 */
package edu.curtin.app;
import java.io.IOException;


public class Menu
{
    public static final String FILE_EXTENSION = ".txt";

    /************************************************************
    * IMPORT: none
    * EXPORT: void
    * ASSERTION: Displays menu, launches game and loads input
    ************************************************************/
    public static void showMenu()
    {
        boolean done = false;
        while(!done)
        {
            switch(checkInteger("(1) Start Simulation (2) Help (0) Quit"))
            {
                case 1://Option Play
                    start();

                    break;

                case 2:
                    System.out.print("Testing"); //TODO STUB
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
    ASSERTION: Starts sim - loads file
    ************************************************************/
    private static void start()
    {
        String fileName = checkFileName(FILE_EXTENSION); //gets file name from user
        Simulation sim = new Simulation();

        try
        {
            sim = FileIO.readFile(fileName);
            sim.printEventList();

        }
        catch (IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    /************************************************************
    IMPORT: file extension (String)
    EXPORT: filename (String)
    ASSERTION: Lets user enter the file name and validates input
    ************************************************************/
    public static String checkFileName(String ext)
    {
        String prompt = "Please enter a file name:";
        int ans;

        System.out.println(prompt);
        String filename = Keyboard.next() + ext;
        prompt = "File name: <" + filename + ">\nIs this correct? [1]Confirm [2]Cancel\n";
        do
        {
            Keyboard.nextLine(); //clears keyboard buffer
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
