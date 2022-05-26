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
public class FileIO<E>{

    /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    /************************************************************
    IMPORT: filename (String)
    EXPORT: sim (EventNotifier)
    ASSERTION: Imports a text file and writes it to an object
    ************************************************************/
    public void readFile(String filename, EventNotifier<E> en) throws IOException
    {
        File inFile = new File(filename);
        Scanner sc = new Scanner(inFile);

        while(sc.hasNextLine())
        {
            int time = sc.nextInt(); //time
            String location;
            String eType;
            String command = sc.next(); //gets command to read
            command = command.toUpperCase(); //converts to upper case

            if(command.equals("FLOOD") || command.equals("FIRE") || command.equals("CHEMICAL"))//valid
            {
                eType = command;
            }
            else //invalid type
            {
                String err = "Invalid emergency type in input file: " + command;
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, err);
                }
                sc.close(); //close scanner
                throw new IOException(err);
            }
            location = sc.nextLine(); //location

            if(en.checkDupes(eType, location)) //checks for duplicate events - throws IO exception
            {
                String err = "Duplicate " + eType + " event at: " + location;
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, err);
                }
                sc.close(); //close scanner
                throw new IOException(err);
            }

            en.addEvent(time, eType, location);
        }

        sc.close(); //close scanner
    }

}