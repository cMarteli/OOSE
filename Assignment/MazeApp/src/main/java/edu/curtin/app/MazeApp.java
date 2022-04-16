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

public class MazeApp extends Graphics
{

    public static final String SPLASH = YELLOW+ "*************************\n" + RESET + "*\tMAZE APP\t*" + MAGENTA + "\n*************************\n" + RESET;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try
        {
            System.out.println(SPLASH); //displays title

            Menu.showMenu();

        }
        catch (Exception e) //only general exception to let program "fail gracefully"
        {
            System.out.println("Error: " + e);
            System.out.println("The Program will now close...");
        }
        finally
        {
            Keyboard.close(); //closes Scanner therefore closing System.in - only to satisfy PMD
        }

    }



}
