/**
 * MazeApp.java
 * Main class MazeApp, handles command line parameters
 * and catches any unhandled exceptions that may have fallen through.
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
// Marteli, C (2021) DSA source code (Version 1.0) [Source code]. https://github.com/cMarteli/
// Some methods previously submitted for DSA final assignment Modified in 2022 for MazeApp.java
package edu.curtin.app;

//import java.util.ArrayList;
//import java.util.List;

public class MazeApp extends Graphics
{

    public static final String SPLASH = YELLOW+ "*************************\n" + RESET + "*\tMAZE APP\t*" + MAGENTA + "\n*************************\n" + RESET;
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
            // Maze m = new Maze(4, 6);
            // m.moveCursor('s');
            // m.moveCursor('e');
            // m.moveCursor('s');
            // m.moveCursor('e');
            // m.moveCursor('n');
            // m.moveCursor('w');
            // m.moveCursor('w');
            // m.moveCursor('n');
            // m.moveCursor('w');
            // m.moveCursor('s');

            //System.out.println("\u253c");


            Menu.showMenu();

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            System.out.println("The Program will now close...");
        }
        finally
        {
            Keyboard.close(); //only to satisfy PMD
        }

    }



}
