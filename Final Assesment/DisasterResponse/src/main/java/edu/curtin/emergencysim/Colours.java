/**
 * Constants.java
 * Final class, contains ANSI codes for colour and constant values
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
public final class Colours{
    /**
     * GFX
     */

    //Reset colour to default
    public static final String RESET = "\033[m";

    //Clear Screen
    public static final String CLEAR = "\033[2J";

    //Reset Cursor
    public static final String RESET_CURSOR = "\033[H";

    //Colours
    public static final String RED = "\033[31m";     // RED
    public static final String GREEN = "\033[32m";   // GREEN
    public static final String YELLOW = "\033[33m";  // YELLOW
    public static final String BLUE = "\033[34m";    // BLUE
    public static final String MAGENTA = "\033[35m";  // MAGENTA
    public static final String CYAN = "\033[36m";    // CYAN
}
