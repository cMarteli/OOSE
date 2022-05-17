/**
 * Graphics.java
 * Final class, contains ANSI codes for colour and special character display
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app;
public final class Graphics
{
    public static final String FILE_EXTENSION = ".txt"; //Hardcoded value for input file extension

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

    //symbols
    public static final String EDG_HOR = "\u2500"; // ─  
    public static final String EDG_VER = "\u2502"; // │
    public static final String CNR_TOP_L = "\u250c"; // ┌ 
    public static final String CNR_TOP_R = "\u2510"; // ┐
    public static final String CNR_BTM_L = "\u2514"; // └ 
    public static final String CNR_BTM_R = "\u2518"; // ┘
    public static final String JOIN_L = "\u251c"; // ├
    public static final String JOIN_R = "\u2524"; // ┤
    public static final String JOIN_TOP = "\u252c"; // ┬
    public static final String JOIN_BTM = "\u2534"; // ┴
    public static final String JOIN_MID = "\u253c"; // ┼

    public static final String DOOR_SYMBOL = "\u2592"; // ▒
    public static final String KEY_SYMBOL = "\u2555"; // ╕

    //game symbols

    //doors
    public static final String RED_DOOR = "\033[31m\u2592\033[m"; // red door
    public static final String GRN_DOOR = "\033[32m\u2592\033[m"; // green door
    public static final String YLW_DOOR = "\033[33m\u2592\033[m"; // yellow door
    public static final String BLU_DOOR = "\033[34m\u2592\033[m"; // blue door
    public static final String MGT_DOOR = "\033[35m\u2592\033[m"; // magenta door
    public static final String CYA_DOOR = "\033[36m\u2592\033[m"; // cyan door

    //keys
    public static final String RED_KEY = "\033[31m\u2555\033[m"; // red key
    public static final String GRN_KEY = "\033[32m\u2555\033[m"; // green key
    public static final String YLW_KEY = "\033[33m\u2555\033[m"; // yellow key
    public static final String BLU_KEY = "\033[34m\u2555\033[m"; // blue key
    public static final String MGT_KEY = "\033[35m\u2555\033[m"; // magenta key
    public static final String CYA_KEY = "\033[36m\u2555\033[m"; // cyan key

    //player
    public static final String PLAYER_SYMBOL = "\033[31mP\033[m"; // red 'P'

    //Horizontal wall
    public static final String WALL_HOR = "\u2500\u2500\u2500"; // ─── 




    //function to test graphics display
    public static void gfxTest()
    {
        // System.out.println(RED + "RED" + RESET);
        // System.out.println(GREEN + "GREEN" + RESET);
        // System.out.println(YELLOW + "YELLOW" + RESET);
        // System.out.println(BLUE + "BLUE" + RESET);
        // System.out.println(MAGENTA + "MAGENTA" + RESET);
        // System.out.println(CYAN + "CYAN" + RESET);

        System.out.println(EDG_HOR);
        System.out.println(KEY_SYMBOL);

        System.out.println(RED_DOOR);
        System.out.println(GRN_DOOR);
        System.out.println(YLW_DOOR);
        System.out.println(BLU_DOOR);
        System.out.println(MGT_DOOR);
        System.out.println(CYA_DOOR);

        System.out.println(RED_KEY);
        System.out.println(GRN_KEY);
        System.out.println(YLW_KEY);
        System.out.println(BLU_KEY);
        System.out.println(MGT_KEY);
        System.out.println(CYA_KEY);

    }


}
