/**
 * EmergencyResponse.java
 * Main class of Emergency Response App
 * Catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2022) OOSE source code (Version 1.2) [Source code]. https://github.com/cMarteli/
 * Program may contain methods previously submitted for DSA/OOSE
 * Modified May,2022 for EmergencyResponse.java
 */
package edu.curtin.emergencysim;

import static edu.curtin.emergencysim.Constants.*; //imports GFX class
import java.util.logging.*;

@SuppressWarnings("PMD.AvoidCatchingGenericException") //See line 29
public class EmergencyResponse
{

    private static final String SPLASH = BLUE+ "**************************\n*" + RESET + " EMERGENCY RESPONSE SIM " + MAGENTA + "*\n**************************\n" + RESET;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   //     try
     //   {
            setupLogger();// sets up logging
            System.out.println(SPLASH); //displays title

            Menu.showMenu();

        // }
        // catch (Exception e) //only generic exception to let program "fail gracefully" still returns error to user and is logged
        // {
        //     if (LOGR.isLoggable(Level.SEVERE))
        //     {
        //         LOGR.log(Level.SEVERE, "Total Crash: " + e.getMessage());
        //     }
        //     System.out.println("Error: " + e.getMessage());
        //     System.out.println("The Program will now close...");
        // }
        // finally
        // {
        //     Keyboard.close(); //closes Scanner therefore closing System.in - to satisfy PMD
        // }

    }

    /**
     * Logger
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());
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
