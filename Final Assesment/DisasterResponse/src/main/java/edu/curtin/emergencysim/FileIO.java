/**
 * FileIO.java
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * may contain code previously submitted for DSA Modified March 2022 for EmergencyResponse.java
 * Modified May,2022 for EmergencyResponse.java
*/
package edu.curtin.emergencysim;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import edu.curtin.emergencysim.notifier.*;

@SuppressWarnings("PMD.CloseResource") //Scanner is closed, checked with VSCODE linting tool
public class FileIO<E>{

    /**
     * Logger from EmergencyResponse.java
     */
    private final static Logger LOGR = Logger.getLogger(EmergencyResponse.class.getName());

    /************************************************************
     * Imports a text file and writes it to an object
     * @param fileName (String)
     * @param en (EventNotifier)
     * @throws IOException
     ************************************************************/
    public void readFile(String fileName, EventNotifier<E> en) throws IOException
    {
        File inFile = new File(fileName);
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


    /************************************************************
     * Lets user enter the file name and validates input
     * @param ext (String)
     * @return filename (String)
     ************************************************************/
    public String checkFileName(String ext)
    {
        String prompt = "Please enter a file name:";
        int ans;

        System.out.println(prompt);
        String filename = Keyboard.next() + ext;
        prompt = "File name: <" + filename + ">\nIs this correct? [1]Confirm [2]Cancel\n";
        do
        {
            Keyboard.nextLine(); //clears keyboard buffer
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
     * Validator Method. Gets user integer and repeats until it's a valid input
     * @param prompt (String)
     * @return userInt (integer)
    ************************************************************/
    public int checkInteger(String prompt)
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
                System.out.println("Enter a number");
            }
        }
        return userInt;
    }

}