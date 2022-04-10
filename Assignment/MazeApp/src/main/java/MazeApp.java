/**
 * MazeApp.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;

public class MazeApp
{

    public static final String SPLASH = "******************\n* MAZE APP	*\n******************\n";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try
        {
            System.out.println(SPLASH);

            // TEST HARNESS FOR KEYS:
            // List<Key> l1 = new ArrayList<>();
            // Key k1 = new RedKey();
            // l1.add(k1);
            // //System.out.println(k1.description());
            // Key k2 = new BlueKey();
            // l1.add(k2);
            // //System.out.println(k2.description());

            // for(Key k : l1)
            // {
            //     System.out.println(k.description());
            // }

            //TEST HARNESS FOR MAZE
            Maze m = new Maze(4, 4);



            //Menu.showMenu();

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }



}
