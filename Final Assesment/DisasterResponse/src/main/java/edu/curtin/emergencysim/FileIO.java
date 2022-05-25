/**
 * FileIO.java
 * @author Caio Marteli (19598552)
*/
// Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// # may contain code previously submitted for DSA Modified March 2022 for EmergencyResponse.java
// Modified May,2022 for EmergencyResponse.java
package edu.curtin.emergencysim;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
@SuppressWarnings("PMD.CloseResource") //Scanner is closed, checked with VSCODE linting tool
public class FileIO {

    /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    /************************************************************
    IMPORT: filename (String)
    EXPORT: sim (EventNotifier)
    ASSERTION: Imports a text file and writes it to an object
    ************************************************************/
    public static EventNotifier<Event> readFile(String filename) throws IOException
    {
        File inFile = new File(filename);
        Scanner sc = new Scanner(inFile);

        EventNotifier<Event> sim = new EventNotifierImpl();

        while(sc.hasNextLine())
        {
            int time = sc.nextInt(); //time
            String location;
            Event.Emergency dis;
            String command = sc.next(); //gets command to read


            if(command.toLowerCase().equals("flood"))//case flood
            {
                dis = Event.Emergency.FLOOD;
            }
            else if(command.toLowerCase().equals("fire")) //case fire
            {
                dis = Event.Emergency.FIRE;
            }
            else if(command.toLowerCase().equals("chemical")) //case chemical
            {
                dis = Event.Emergency.CHEMICAL;
            }
            else //invalid
            {
                String err = "Invalid command in input file: " + command;
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, err);
                }
                sc.close(); //close scanner
                throw new IOException(err);
            }
            location = sc.nextLine(); //location

            Event e = new Event(time, dis, location);

            if(sim.checkDupes(e)) //checks for duplicate events - throws IO exception
            {
                String err = "Duplicate event: " + e.toString();
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, err);
                }
                sc.close(); //close scanner
                throw new IOException(err);
            }

            sim.addEvent(e);
        }

        sc.close(); //close scanner
        return sim;
    }

}