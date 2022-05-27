/**
 * EmergencyResponse.java
 * MAIN class of Emergency Response App
 * Instatiates FileIO, EventNotifier, ResponderComm and Simulation classes
 * serves as an injector to Simulation class and performs error handling for fileIO
 * Also stores Logger method
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 * Marteli, C (2022) OOSE source code (Version 1.2) [Source code]. https://github.com/cMarteli/
 * Program may contain parts of code previously submitted for DSA/OOSE
 * Modified May,2022 for EmergencyResponse.java
 */
package edu.curtin.emergencysim;

import static edu.curtin.emergencysim.Constants.*; //imports GFX class
import edu.curtin.emergencysim.responders.*;
import java.util.logging.*;

public class EmergencyResponse
{

    private static final String SPLASH = BLUE+ "**************************\n*" + RESET + " EMERGENCY RESPONSE SIM " + MAGENTA + "*\n**************************\n" + RESET;
    /**
     * @param args the command line arguments
     */
    private static final String FILE_EXTENSION = ".txt";
    public static void main(String[] args)
    {
        setupLogger();// sets up logging
        System.out.println(SPLASH); //displays splash title

        //instantiates classes to be injected into simulation
        FileIO<Event> fio = new FileIO<>(); //creates new file IO object that uses event
        EventNotifier<Event> en = new EventNotifierImpl(); //Event notifier class
        ResponderComm rci;
        Simulation sim;

        String fileName = fio.checkFileName(FILE_EXTENSION); //gets file name from user

        try
        {
            fio.readFile(fileName, en); //loads file into EventNotifier
            rci = new ResponderCommImpl(); //needs to be just before run() to avoid clock desyncs
            sim = new Simulation(en, rci);
            sim.run(); //starts simulation
        }
        catch (java.io.IOException e) //for fio.readFile
        {
            if (LOGR.isLoggable(Level.FINE)){
                LOGR.log(Level.FINE, "IO Exception: " + e.getMessage());
            }
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
        catch (InterruptedException ex) //for sim.run()
        {
            if (LOGR.isLoggable(Level.SEVERE)){
                LOGR.log(Level.SEVERE, "Interrupted Exception: " + ex.getMessage());
            }
            System.out.println("Simulation was interupted: " + ex.getMessage());
        }
        finally
        {
            System.out.println("The Program will now close...");
            Keyboard.close(); //closes Scanner therefore closing System.in - to satisfy PMD
        }

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
