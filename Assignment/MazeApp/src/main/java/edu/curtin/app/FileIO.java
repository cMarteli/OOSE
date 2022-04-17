/**
 * FileReader.java
 * @author Caio Marteli (19598552)
*/
// Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// # previously submitted for DSA Modified March 2022 for MazeApp.java
package edu.curtin.app;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("PMD.CloseResource") //Scanner is closed, checked with VSCODE linting tool, see lines 82,89
public class FileIO {

     /**
     * Logger from MazeApp.java
     */
    private final static Logger LOGR = Logger.getLogger(MazeApp.class.getName());


    /************************************************************
    IMPORT: filename (String)
    EXPORT: m (Maze)
    ASSERTION: Imports a text file and writes it to a maze object
    ************************************************************/
    public static Maze readMazeFile(String filename) throws IOException
    {
        File inFile = new File(filename);
        Scanner sc = new Scanner(inFile);
        int row = sc.nextInt();
        int col = sc.nextInt();
        Maze m = new Maze(row, col); //creates maze with parameters on first line

        while(sc.hasNextLine())
        {
            String command = sc.next(); //gets command to read

            if(command.equals("WV"))//case finds WV
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                m.addVertWalls(x, y);
            }
            else if(command.equals("WH")) //case finds WH
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                m.addHoriWalls(x, y);
            }
            else if(command.equals("S")) //case finds S
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                m.setStart(x,y);
            }
            else if(command.equals("M")) //case finds Message
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                String message = sc.nextLine();
                m.addTiles(x, y, message);
            }
            else if(command.equals("E")) //case finds end point
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                m.setEnd(x, y);
            }
            else if(command.equals("DV"))
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int clr = sc.nextInt();
                m.addDoors(x, y, clr, "VERTICAL");
            }
            else if(command.equals("DH"))
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int clr = sc.nextInt();
                m.addDoors(x, y, clr, "HORIZONTAL");
            }
            else if(command.equals("K"))
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int clr = sc.nextInt();
                m.addKeys(x, y, clr);

            }
            else
            {
                if (LOGR.isLoggable(Level.FINE))
                {
                    LOGR.log(Level.FINE, "Invalid command in input file: " + command);
                }
                sc.close(); //close scanner
                throw new IOException("Invalid command in input file: " + command);

            }

        }

        sc.close(); //close scanner
        return m;
    }

}