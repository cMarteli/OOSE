/**
 *
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552), Carey Brown (https://coderanch.com/t/685118/java/FYI-wrapper-class-Scanner-System, 2018)
 */
package edu.curtin.app;
public abstract class Graphics
{
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
    public static final String BORDER_01 = "\u2500"; // ─  
    public static final String BORDER_02 = "\u2502"; // │
    public static final String BORDER_03 = "\u250c"; // ┌ 
    public static final String BORDER_04 = "\u2510"; // ┐
    public static final String BORDER_05 = "\u2514"; // └ 
    public static final String BORDER_06 = "\u2518"; // ┘
    public static final String BORDER_07 = "\u251c"; // ├
    public static final String BORDER_08 = "\u2524"; // ┤
    public static final String BORDER_09 = "\u252c"; // ┬
    public static final String BORDER_10 = "\u2534"; // ┴
    public static final String BORDER_11 = "\u253c"; // ┼

    public static final String DOOR_SYMBOL = "\u2592"; // ▒
    public static final String KEY_SYMBOL = "\u2555"; // ╕

    //game symbols

    //doors
    public static final String RED_DOOR = RED + DOOR_SYMBOL + RESET; // red door
    public static final String GRN_DOOR = GREEN + DOOR_SYMBOL + RESET; // green door
    public static final String YLW_DOOR = YELLOW + DOOR_SYMBOL + RESET; // yellow door
    public static final String BLU_DOOR = BLUE + DOOR_SYMBOL + RESET; // blue door
    public static final String MGT_DOOR = MAGENTA + DOOR_SYMBOL + RESET; // magenta door
    public static final String CYA_DOOR = CYAN + DOOR_SYMBOL + RESET; // cyan door

    //keys
    public static final String RED_KEY = RED + KEY_SYMBOL + RESET; // red key
    public static final String GRN_KEY = GREEN + KEY_SYMBOL + RESET; // green key
    public static final String YLW_KEY = YELLOW + KEY_SYMBOL + RESET; // yellow key
    public static final String BLU_KEY = BLUE + KEY_SYMBOL + RESET; // blue key
    public static final String MGT_KEY = MAGENTA + KEY_SYMBOL + RESET; // magenta key
    public static final String CYA_KEY = CYAN + KEY_SYMBOL + RESET; // cyan key




    //function to test graphics display
    public static void gfxTest()
    {
        // System.out.println(RED + "RED" + RESET);
        // System.out.println(GREEN + "GREEN" + RESET);
        // System.out.println(YELLOW + "YELLOW" + RESET);
        // System.out.println(BLUE + "BLUE" + RESET);
        // System.out.println(MAGENTA + "MAGENTA" + RESET);
        // System.out.println(CYAN + "CYAN" + RESET);

        System.out.println(BORDER_01);
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
