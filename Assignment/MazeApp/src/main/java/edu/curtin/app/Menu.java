/**
 * Menu.java
 * Displays user Menus and validates keyboard input
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * Some methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
 */
package edu.curtin.app;

public class Menu
{


    /************************************************************
    Displays menu and launches game
    ************************************************************/
    public static void showMenu()
    {
        boolean fileLoaded = false;
        boolean done = false;
        while(!done)
        {
            switch(checkInteger("(1) Play (2) Load File (3) Help (0) Quit"))
            {
                case 1://Option Play
                    if(fileLoaded)
                    {
                        play();//game start
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
    }

    /************************************************************
    IMPORT:
    EXPORT:
    ASSERTION: Starts game
    ************************************************************/
    private static void play()
    {
        System.out.print("Starting Game");
        Game g = new Game(4, 6);//TODO Hardcoded change to obtained from file
    }

    /************************************************************
    IMPORT:
    EXPORT:
    ASSERTION: Loads file using FileReader class
    ************************************************************/
    private static boolean loadFile()
    {
        String fileName = checkFileName(".txt");
        //TODO: call filereader method
        return true;//change to return success or fail

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
                System.out.println("Enter a valid number");
            }
        }

        return userInt;
    }

}
