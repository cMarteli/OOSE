/**
 * MazeApp.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
// Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// May contain methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
package edu.curtin.app;

import static edu.curtin.app.Graphics.*; //imports GFX class

import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
@SuppressWarnings("PMD.AvoidCatchingGenericException") //See line 30
public class MazeApp
{

    public static final String SPLASH = YELLOW+ "*************************\n" + RESET + "*\tMAZE APP\t*" + MAGENTA + "\n*************************\n" + RESET;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try
        {
            setupLogger();// sets up logging
            System.out.println(SPLASH); //displays title

            Menu.showMenu();

        }
        catch (Exception e) //only generic exception to let program "fail gracefully" still returns error to user and is logged
        {
            if (LOGR.isLoggable(Level.SEVERE))
            {
                LOGR.log(Level.SEVERE, "Total Crash: " + e);
            }
            System.out.println("Error: " + e);
            System.out.println("The Program will now close...");
        }
        finally
        {
            Keyboard.close(); //closes Scanner therefore closing System.in - to satisfy PMD
        }

    }

    /**
     * Logger
     */
    private final static Logger LOGR = Logger.getLogger(MazeApp.class.getName());
    private static void setupLogger() {
        LogManager.getLogManager().reset();
        LOGR.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGR.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Mylog.log", true);
            fh.setLevel(Level.FINE);
            LOGR.addHandler(fh);
        } catch (java.io.IOException e)
        {
            LOGR.log(Level.SEVERE, "Logger broke.", e);
        }
    }


}
