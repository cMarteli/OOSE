package edu.curtin.app;

public class MazeApp
{
/**
 * MazeApp.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
    public static final String SPLASH = "******************\n* MAZE APP	*\n******************\n";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try
        {
            System.out.println(SPLASH);
            UserInterface.showMenu();

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }



}
