/**
 * EmergencyResponse.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
// Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// May contain methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
package edu.curtin.emergencysim;
import static edu.curtin.emergencysim.Constants.*; //imports GFX class

public class EmergencyResponse
{

    public static final String SPLASH = BLUE+ "**************************\n*" + RESET + " EMERGENCY RESPONSE SIM " + MAGENTA + "*\n**************************\n" + RESET;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
            System.out.println(SPLASH); //displays title
            // boolean simIsActive = true; //TODO: Hardcoded
            // int seconds = 0;
            // while (simIsActive) {
            //     //if check() == true;  //check if anything has happened
            //         //action(); //if so perform action
            //     System.out.println(seconds + "s");
            //     Thread.sleep(1000);
            //     seconds++;
            // }
            Menu.showMenu();

        }
        catch (Exception e) //only generic exception to let program "fail gracefully" still returns error to user and is logged
        {
            System.out.println("Error: " + e);
            System.out.println("The Program will now close...");
        }
        finally
        {
            Keyboard.close(); //closes Scanner therefore closing System.in - to satisfy PMD
        }

    }


}
