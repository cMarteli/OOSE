/**
 * FileReader.java
 * @author Caio Marteli (19598552)
*/
// Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// # previously submitted for DSA Modified March 2022 for MazeApp.java
package edu.curtin.app;
import edu.curtin.app.tiles.*;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import java.io.File;
import java.awt.Point;

//@SuppressWarnings("unchecked")
public class FileReader {


/************************************************************
IMPORT: filename (String)
EXPORT: graph (DSAGraph)
ASSERTION: Imports a text file and writes it to a graph
************************************************************/
// public static String[][] readFile(String filename)
//     {
//         String[][] maze;
//         // = new new String[rows][columns];
//         System.out.println("Reading file: " + filename);
//         try {
//             File inFile = new File(filename);
//             Scanner sc = new Scanner(inFile);
//             //sc.skip("#"); //skips comment at beggining
//             System.out.println(sc.nextInt());//DEBUG
//             //sc.useDelimiter(" ");

//             while(sc.hasNextLine())
//             {
//                 //sc.nextLine(); //currently always skipping the first line
//                 String command = sc.next();

//                 if(command.equals("WV"))//case finds WV
//                 {
//                     String label = sc.next();
//                     graph.addVertex(label);
//                 }
//                 else if(command.equals("WH")) //case finds WH
//                 {
//                     int l1 = sc.nextInt();
//                     int l2 = sc.nextInt();
//                     graph.addEdge(l1, l2);
//                 }
//                 // else if(command.equals("#"))
//                 // {
//                 //     System.out.println("Comment line"); //may crash if there's a comment after the '#'
//                 // }

//             }
//             sc.close();
//         } catch (Exception e) //file not found
//         {
//             throw new IllegalArgumentException("Unable to load object from file" + e.getMessage());
//         }

//         return graph;
    //TODO: STUB should read from file
    public static Map<Point, Tile> readFile(String filename)
    {
        Map m = new HashMap<Point, Tile>();

        Point playerStart = new Point(0,0); //TODO: get this from file reader not here
        List<Point> vWalls = new LinkedList<>(); //get this from file reader not here
        List<Point> hWalls = new LinkedList<>(); //get this from file reader not here
        //TODO: get this from file reader not here
        vWalls.add(new Point(0,1));
        vWalls.add(new Point(0,3));
        vWalls.add(new Point(1,3));
        vWalls.add(new Point(2,1));
        vWalls.add(new Point(3,2));

        hWalls.add(new Point(1,1));
        hWalls.add(new Point(2,0));
        hWalls.add(new Point(2,2));
        hWalls.add(new Point(3,2));


        return m;

    }

    //}//end readfile()

}