/**
 * FileIO.java
 * @author Caio Marteli (19598552)
 * Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
 * may contain code previously submitted for DSA Modified March 2022 for EmergencyResponse.java
 * Modified May,2022 for EmergencyResponse.java
*/
package edu.curtin.emergencysim.io;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

import edu.curtin.emergencysim.EmergencySim;
import edu.curtin.emergencysim.events.Event;

@SuppressWarnings("PMD.CloseResource") //Scanner is closed, checked with VSCODE linting tool
public class FileIO<E>{

    /** Logger from EmergencyResponse.java   */
    private final static Logger LOGR = Logger.getLogger(EmergencySim.class.getName());

    /************************************************************
     * Imports a text file and writes it to an object
     * @param fileName (String)
     * @return list (List<Event>)
     * @throws IOException
     ************************************************************/
    public List<Event> readFile(String fileName) throws IOException
    {
        File inFile = new File(fileName);
        Scanner sc = new Scanner(inFile);
        List<Event> list = new ArrayList<>();

        while(sc.hasNextLine())
        {
            Event temp;
            int time = sc.nextInt(); //time
            String location;
            String eType;
            String command = sc.next(); //gets command to read
            command = command.toLowerCase(); //converts to lower case

            if(command.equals("flood") || command.equals("fire") || command.equals("chemical"))//valid
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
            location = sc.nextLine().trim(); //location

            if(checkDupes(eType, location, list)) //checks for duplicate events - throws IO exception
            {
                String err = "Duplicate " + eType + " event at: " + location;
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, err);
                }
                sc.close(); //close scanner
                throw new IOException(err);
            }
            temp = new Event(time, eType, location);
            list.add(temp);
        }
        sc.close(); //close scanner

        return list;
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
     * @return userInt (Integer)
    ************************************************************/
    public int checkInteger(String prompt)
    {
        int userInt = 0;
        boolean isValid = false;

        while (!isValid)
        {
            try{
                System.out.println(prompt);
                userInt = Integer.parseInt(Keyboard.nextLine());
                isValid = true;
            }
            catch(NumberFormatException e){
                System.out.println("Enter a number");
            }
        }
        return userInt;
    }

    /************************************************************
     * Checks for duplicate events. Used by fileIO.java
     * @param type (String)
     * @param loc (String)
     * @param l (List<Event>)
     * @return result (Boolean)
    ************************************************************/
    public boolean checkDupes(String type, String loc, List<Event> l)
    {
        boolean result = false;
        for (Event ev : l)
        {
            if(ev.compare(type, loc))
            {
                result = true;
            }
        }
        return result;
    }

}