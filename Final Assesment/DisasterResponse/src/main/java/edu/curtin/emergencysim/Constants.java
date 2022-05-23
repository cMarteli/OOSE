/**
 * Constants.java
 * Final class, contains ANSI codes for colour and constant values
 *
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
public final class Constants
{

    //fire
    public static final int FIRE_LOW_TO_HIGH_TIME = 10; //time(seconds) it takes for a low intensity fire to turn high intensity
    public static final int FIRE_LOW_CLEANUP_TIME = 5; //time(seconds) to actively extinguish a low intensity fire
    public static final int FIRE_HIGH_TO_LOW_TIME = 30; //time(seconds) it takes for a high intensity fire to turn low intensity

    public static final double FIRE_LOW_CASUALTY_PROB = 0.15; //low intensity fire - probability (0–1) of hospitalising someone
    public static final double FIRE_LOW_DAMAGE_PROB = 0.45; //low intensity fire - probability of destroying a property
    public static final double FIRE_HIGH_CASUALTY_PROB = 0.45; //high intensity fire - probability of hospitalising someone
    public static final double FIRE_HIGH_DAMAGE_PROB = 0.75; //high intensity fire - probability of destroying a property

    //flood
    public static final int FLOOD_END_TIME = 15; //time(seconds) for a flood to dissipate on it's own

    public static final double FLOOD_DAMAGE_PROB = 0.75; //probability of flood destroying a property
    public static final double FLOOD_CASUALTY_PROB = 0.75; //probability of hospitalisation. (If flood rescuers are present, hospitalisation = 0.)

    //chemical
    public static final int CHEM_CLEANUP_TIME = 5; //time(seconds) to actively clean a chemical spill

    public static final double CHEM_CONTAM_PROB = 0.45; //probability of environmental contamination



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
