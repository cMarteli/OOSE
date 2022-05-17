/**
 * FileReader.java
 * @author Caio Marteli (19598552)
*/
// Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// # previously submitted for DSA Modified March 2022 for MazeApp.java
package edu.curtin.app;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("PMD.CloseResource") //Scanner is closed, checked with VSCODE linting tool, see lines 82,89
public class FileIO {

    /************************************************************
    IMPORT: filename (String)
    EXPORT: m (Maze)
    ASSERTION: Imports a text file and writes it to a maze object
    ************************************************************/
    public static List<Event> readFile(String filename) throws IOException
    {
        File inFile = new File(filename);
        Scanner sc = new Scanner(inFile);

        ArrayList<Event> list = new ArrayList<>();

        while(sc.hasNextLine())
        {
            int time = sc.nextInt(); //time
            String location = " ";
            Event.Disaster dis;
            String command = sc.next(); //gets command to read


            if(command.toLowerCase().equals("flood"))//case flood
            {
                dis = Event.Disaster.FLOOD;
            }
            else if(command.toLowerCase().equals("fire")) //case fire
            {
                dis = Event.Disaster.FIRE;
            }
            else if(command.toLowerCase().equals("chemical")) //case chemical
            {
                dis = Event.Disaster.CHEMICAL;
            }
            else //invalid
            {
                sc.close(); //close scanner
                throw new IOException("Invalid command in input file: " + command);
            }
            location = sc.nextLine(); //location

            Event e = new Event(time, dis, location);
            //System.out.println(e.toString());

            for (Event ev : list) //checks for duplicate events
            {
                if(e.isSame(ev))
                {
                    sc.close(); //close scanner
                    throw new IOException("Duplicate event: " + e.toString());
                }
            }

            list.add(e);

        }

        sc.close(); //close scanner
        return list;
    }

}