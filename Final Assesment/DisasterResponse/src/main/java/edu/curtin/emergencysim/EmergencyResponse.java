/**
 * EmergencyResponse.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
// Marteli, C (2021) OOSE source code (Version 1.2) [Source code]. https://github.com/cMarteli/
// Program may contain methods previously submitted for DSA/OOSE
// Modified May,2022 for EmergencyResponse.java
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

            Menu.showMenu();

        }
        catch (Exception e) //only generic exception to let program "fail gracefully" still returns error to user and is logged
        {
            e.printStackTrace();
            System.out.println("The Program will now close...");
        }
        finally
        {
            Keyboard.close(); //closes Scanner therefore closing System.in - to satisfy PMD
        }

    }


}
